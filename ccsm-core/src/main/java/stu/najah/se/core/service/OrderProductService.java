package stu.najah.se.core.service;

import stu.najah.se.core.DatabaseErrorListener;
import stu.najah.se.core.DatabaseOperationException;
import stu.najah.se.core.EntityListener;
import stu.najah.se.core.dao.OrderProductDAO;
import stu.najah.se.core.entity.OrderEntity;
import stu.najah.se.core.entity.OrderProductEntity;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * <p>
 * Provides services for managing order-product data.
 * It interacts with the database to perform CRUD operations and handles database transactions.
 * </p><p>
 * The class maintains a reference to a specific order-product, which is set or cleared during various operations.
 * When an operation fails due to a database error, the current order-product is cleared.
 * </p><p>
 * DatabaseErrorHandler is required for handling and logging database errors,
 * any error that occurs during transactions will be sent to the handler to deal with.
 * </p><p>
 * OrderProductService also implements EntityListener[OrderEntity] to react to changes in the order
 * managed by OrderService. This allows OrderProductService to update its internal state and the order-products it manages
 * based on the selected order. The implications of this design choice are:
 * </p><ul>
 * <li>OrderProductService stays synchronized with OrderService, ensuring that it manages order-products related to the current order.</li>
 * <li>It establishes a clear relationship between orders and their products, making the system more cohesive and easier to reason about.</li>
 * <li>As OrderProductService reacts to order changes, it can notify its listeners (e.g., UI components) about order-product updates, ensuring a consistent view of the data.</li>
 * </ul>
 */
public class OrderProductService
        implements EntityListener<OrderEntity> {

    private final OrderProductDAO orderProductDAO;

    private final DatabaseErrorListener errorHandler;

    private final OrderService orderService;

    private final ProductService productService;

    private final ObservedEntity<OrderProductEntity> observedOrderProduct = new ObservedEntity<>();

    /**
     * Constructs a new OrderProductService instance.
     *
     * @param orderProductDAO The data access object to interact with the database.
     * @param errorListener   The handler for database errors.
     * @param orderService    The OrderService instance.
     * @param productService  The ProductService instance.
     */
    public OrderProductService(OrderProductDAO orderProductDAO,
                               DatabaseErrorListener errorListener,
                               OrderService orderService,
                               ProductService productService) {
        this.orderProductDAO = orderProductDAO;
        this.errorHandler = errorListener;
        this.orderService = orderService;
        this.productService = productService;
        orderService.watchOrder(this);
    }

    @Override
    public void onEntityChanged(OrderEntity newEntity) {
        clearOrderProduct();
    }

    @Override
    public void onEntityCleared() {
        clearOrderProduct();
    }

    /**
     * Retrieves a list of all OrderProductEntity objects associated with the currently selected order.
     *
     * @return A list of OrderProductEntity objects associated with the currently selected order.
     * @throws IllegalStateException If there is no order selected in the OrderService.
     */
    public List<OrderProductEntity> getAllOrderProducts() throws IllegalStateException {
        try {
            var order = orderService.getOrder().orElseThrow();
            return orderProductDAO.getAll(order.getId());
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("No order selected in the order service");
        }
    }

    /**
     * Registers a listener to be notified when the observed OrderProductEntity changes or is cleared.
     *
     * @param listener The listener to register.
     */
    public void watchOrderProduct(EntityListener<OrderProductEntity> listener) {
        observedOrderProduct.addListener(listener);
    }

    /**
     * Unregisters a listener from receiving notifications when the observed OrderProductEntity changes or is cleared.
     *
     * @param listener The listener to unregister.
     */
    public void unwatchOrderProduct(EntityListener<OrderProductEntity> listener) {
        observedOrderProduct.removeListener(listener);
    }

    /**
     * Retrieves the currently observed OrderProductEntity.
     *
     * @return An Optional containing the observed OrderProductEntity if present,
     * or an empty Optional if no order product is currently observed.
     */
    public Optional<OrderProductEntity> getOrderProduct() {
        return observedOrderProduct.get();
    }

    /**
     * Sets the provided OrderProductEntity as the observed order product.
     * This method should be used when an order product is to be observed for changes and updates.
     *
     * @param orderProduct The OrderProductEntity to set as the observed order product.
     */
    public void setOrderProduct(OrderProductEntity orderProduct) {
        observedOrderProduct.set(orderProduct);
    }

    /**
     * Clears the observed OrderProductEntity, setting it to null.
     * This method should be called when the observed order product is no longer relevant or required.
     */
    public void clearOrderProduct() {
        observedOrderProduct.clear();
    }

    /**
     * Selects the order product with the given orderId and productId, and sets it as the observed OrderProductEntity.
     *
     * @param orderId   The ID of the order.
     * @param productId The ID of the product.
     * @return An Optional containing the selected OrderProductEntity if found, or an empty Optional if not found.
     */
    public Optional<OrderProductEntity> selectOrderProduct(int orderId, int productId) {
        observedOrderProduct.set(orderProductDAO.get(orderId, productId));
        return getOrderProduct();
    }

    /**
     * Creates a new order product, inserts it into the database, and selects it as the observed OrderProductEntity.
     *
     * @param orderProduct The OrderProductEntity to create and insert.
     * @return An Optional containing the created and selected OrderProductEntity if successful,
     * or an empty Optional in case of a database error.
     * @throws IllegalStateException If either the order or product are not selected.
     */
    public Optional<OrderProductEntity> createAndSelectOrderProduct(OrderProductEntity orderProduct)
            throws IllegalStateException {
        try {
            orderProduct.setOrderId(orderService.getOrder().orElseThrow().getId());
            orderProduct.setProductId(productService.getProduct().orElseThrow().getId());
            orderProductDAO.insert(orderProduct);
            return selectOrderProduct(orderProduct.getOrderId(), orderProduct.getProductId());
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("Both order and product must be selected!");
        } catch (DatabaseOperationException e) {
            errorHandler.onTransactionError(e.getMessage());
            clearOrderProduct();
        }
        return getOrderProduct();
    }

    /**
     * Updates the observed OrderProductEntity in the database with the provided data.
     *
     * @param orderProduct The OrderProductEntity containing the updated data.
     * @throws IllegalStateException If no order-product has been selected.
     */
    public void updateOrderProduct(OrderProductEntity orderProduct) throws IllegalStateException {
        try {
            var entity = getOrderProduct().orElseThrow();
            entity.setAllBasic(orderProduct);
            orderProductDAO.update(entity);
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("No order-product has been selected");
        } catch (DatabaseOperationException e) {
            errorHandler.onTransactionError(e.getMessage());
            clearOrderProduct();
        }
    }

    /**
     * Deletes the observed OrderProductEntity from the database and clears it.
     *
     * @throws IllegalStateException If no order-product has been selected.
     */
    public void deleteOrderProduct() throws IllegalStateException {
        try {
            var entity = getOrderProduct().orElseThrow();
            orderProductDAO.delete(entity);
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("No order-product has been selected");
        } catch (DatabaseOperationException e) {
            errorHandler.onTransactionError(e.getMessage());
        } finally {
            clearOrderProduct();
        }
    }
}
