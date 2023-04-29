package stu.najah.se.core.service;

import stu.najah.se.core.DatabaseErrorListener;
import stu.najah.se.core.DatabaseOperationException;
import stu.najah.se.core.EmailException;
import stu.najah.se.core.EntityListener;
import stu.najah.se.core.dao.OrderDAO;
import stu.najah.se.core.dao.OrderProductDAO;
import stu.najah.se.core.entity.CustomerEntity;
import stu.najah.se.core.entity.OrderEntity;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * <p>
 * Provides services for managing order data.
 * It interacts with the database to perform CRUD operations and handles database transactions.
 * </p><p>
 * The class maintains a reference to a specific order, which is set or cleared during various operations.
 * When an operation fails due to a database error, the current order is cleared.
 * </p><p>
 * DatabaseErrorHandler is required for handling and logging database errors,
 * any error that occurs during transactions will be sent to the handler to deal with.
 * </p><p>
 * OrderService also implements EntityListener[CustomerEntity] to react to changes in the customer
 * managed by CustomerService. This allows OrderService to update its internal state and the orders it manages
 * based on the selected customer. The implications of this design choice are:
 * </p><ul>
 * <li>OrderService stays synchronized with CustomerService, ensuring that it manages orders related to the current customer.</li>
 * <li>It establishes a clear relationship between customers and their orders, making the system more cohesive and easier to reason about.</li>
 * <li>As OrderService reacts to customer changes, it can notify its listeners (e.g., UI components) about order updates, ensuring a consistent view of the data.</li>
 * </ul>
 */
public class OrderService
        implements EntityListener<CustomerEntity> {

    private static final String NO_ORDER_MESSAGE =
            "No order has been selected";
    private static final String NO_CUSTOMER_MESSAGE =
            "No customer selected in the customer service";

    private final OrderDAO orderDAO;

    private final OrderProductDAO orderProductDAO;

    private final DatabaseErrorListener errorHandler;

    private final CustomerService customerService;

    private final ObservedEntity<OrderEntity> observedOrder = new ObservedEntity<>();

    /**
     * Constructs a new OrderService instance.
     *
     * @param orderDAO        The data access object to interact with the order in the database.
     * @param orderProductDAO The data access object to interact with the products of the order in the database.
     * @param errorListener   The handler for database errors.
     * @param customerService The customer service instance.
     */
    public OrderService(OrderDAO orderDAO,
                        OrderProductDAO orderProductDAO,
                        DatabaseErrorListener errorListener,
                        CustomerService customerService) {
        this.orderDAO = orderDAO;
        this.orderProductDAO = orderProductDAO;
        this.errorHandler = errorListener;
        this.customerService = customerService;
        customerService.watchCustomer(this);
    }

    @Override
    public void onEntityChanged(CustomerEntity newEntity) {
        clearOrder();
    }

    @Override
    public void onEntityCleared() {
        clearOrder();
    }

    /**
     * Retrieves a list of all orders for the current customer in the customer service.
     *
     * @return A list of OrderEntity objects associated with the current customer.
     * @throws IllegalStateException if no customer is selected in the customer service.
     */
    public List<OrderEntity> getAllCustomerOrders() throws IllegalStateException {
        try {
            var customer = customerService.getCustomer().orElseThrow();
            return orderDAO.getAll(customer.getId());
        } catch (NoSuchElementException e) {
            throw new IllegalStateException(NO_CUSTOMER_MESSAGE);
        }
    }

    /**
     * Subscribes a listener to be notified of changes or clearing of the order.
     *
     * @param listener The EntityListener to subscribe for notifications.
     */
    public void watchOrder(EntityListener<OrderEntity> listener) {
        observedOrder.addListener(listener);
    }

    /**
     * Unsubscribes a listener from receiving notifications about changes or clearing of the order.
     *
     * @param listener The EntityListener to unsubscribe from notifications.
     */
    public void unwatchOrder(EntityListener<OrderEntity> listener) {
        observedOrder.removeListener(listener);
    }

    /**
     * Retrieves the current order.
     *
     * @return An Optional containing the current order or empty if no order is set.
     */
    public Optional<OrderEntity> getOrder() {
        return observedOrder.get();
    }

    /**
     * Sets the current order.
     * providing null value has the same effect as calling clearOrder().
     *
     * @param order The order to set as the current order.
     */
    public void setOrder(OrderEntity order) {
        observedOrder.set(order);
    }

    /**
     * Clears the current order.
     */
    public void clearOrder() {
        observedOrder.clear();
    }

    /**
     * Selects an order by ID and sets it as the current order.
     *
     * @param orderId The ID of the order to select.
     * @return An Optional containing the selected order or empty if not found.
     */
    public Optional<OrderEntity> selectOrder(int orderId) {
        observedOrder.set(orderDAO.get(orderId));
        return getOrder();
    }

    /**
     * Creates a new order for the current customer and sets it as the current order.
     * If the transaction fails, the current order is cleared.
     *
     * @return An Optional containing the created order or empty if the transaction failed.
     * @throws IllegalStateException if no customer is selected
     */
    public Optional<OrderEntity> createAndSelectOrder() throws IllegalStateException {
        try {
            var order = new OrderEntity();
            order.setCustomerId(customerService.getCustomer().orElseThrow().getId());
            orderDAO.insert(order);
            return selectOrder(order.getId());
        } catch (NoSuchElementException e) {
            throw new IllegalStateException(NO_CUSTOMER_MESSAGE);
        } catch (DatabaseOperationException e) {
            errorHandler.onTransactionError(e.getMessage());
            clearOrder();
        }
        return getOrder();
    }

    /**
     * Deletes the current order from the database.
     * Whether the transaction fails or not, the current order is cleared.
     *
     * @throws IllegalStateException if no order has been selected.
     */
    public void deleteOrder() throws IllegalStateException {
        try {
            var entity = getOrder().orElseThrow();
            orderDAO.delete(entity);
        } catch (NoSuchElementException e) {
            throw new IllegalStateException(NO_ORDER_MESSAGE);
        } catch (DatabaseOperationException e) {
            errorHandler.onTransactionError(e.getMessage());
        } finally {
            clearOrder();
        }
    }

    /**
     * Calculates the price of the current order by summing up the prices of all its products.
     *
     * @return the price of the current order.
     * @throws IllegalStateException if no order has been selected.
     */
    public int getOrderPrice() throws IllegalStateException {
        try {
            var order = getOrder().orElseThrow();
            var products = orderProductDAO.getAll(order.getId());
            int price = 0;
            for (var product : products) {
                price += product.getPrice();
            }
            return price;
        } catch (NoSuchElementException e) {
            throw new IllegalStateException(NO_ORDER_MESSAGE);
        }
    }

    /**
     * Checks whether the order is finished by checking all of its products.
     * An order is finished if and only if all its products are finished.
     *
     * @return whether the current order has finished.
     * @throws IllegalStateException if no order has been selected.
     */
    public boolean isOrderFinished() throws IllegalStateException {
        try {
            var order = getOrder().orElseThrow();
            var products = orderProductDAO.getAll(order.getId());
            for (var product : products) {
                if (product.getFinished() == 0) {
                    return false;
                }
            }
            return true;
        } catch (NoSuchElementException e) {
            throw new IllegalStateException(NO_ORDER_MESSAGE);
        }
    }

    public void notifyCustomer() throws IllegalStateException, EmailException {

    }
}
