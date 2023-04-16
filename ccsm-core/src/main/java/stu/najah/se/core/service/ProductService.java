package stu.najah.se.core.service;

import stu.najah.se.core.DatabaseErrorListener;
import stu.najah.se.core.DatabaseOperationException;
import stu.najah.se.core.EntityListener;
import stu.najah.se.core.dao.ProductDAO;
import stu.najah.se.core.entity.ProductEntity;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * <p>
 * Provides services for managing product data.
 * It interacts with the database to perform CRUD operations and handles database transactions.
 * </p><p>
 * The class maintains a reference to a specific product, which is set or cleared during various operations.
 * When an operation fails due to a database error, the current product is cleared.
 * </p><p>
 * DatabaseErrorHandler is required for handling and logging database errors,
 * any error that occurs during transactions will be sent to the handler to deal with.
 * </p>
 */
public class ProductService {

    private final ProductDAO productDAO;

    private final DatabaseErrorListener errorHandler;

    private final ObservedEntity<ProductEntity> observedProduct = new ObservedEntity<>();

    /**
     * Constructs a new ProductService instance.
     *
     * @param productDAO    The data access object to interact with the database.
     * @param errorListener The handler for database errors.
     */
    public ProductService(ProductDAO productDAO, DatabaseErrorListener errorListener) {
        this.productDAO = productDAO;
        this.errorHandler = errorListener;
    }

    /**
     * Subscribes a listener to be notified of changes or clearing of the product.
     *
     * @param listener The EntityListener to subscribe for notifications.
     */
    public void watchProduct(EntityListener<ProductEntity> listener) {
        observedProduct.addListener(listener);
    }

    /**
     * Unsubscribes a listener from receiving notifications about changes or clearing of the product.
     *
     * @param listener The EntityListener to unsubscribe from notifications.
     */
    public void unwatchProduct(EntityListener<ProductEntity> listener) {
        observedProduct.removeListener(listener);
    }

    /**
     * Retrieves the current product.
     *
     * @return An Optional containing the current product or empty if no product is set.
     */
    public Optional<ProductEntity> getProduct() {
        return observedProduct.get();
    }

    /**
     * Sets the current product.
     * providing null value has the same effect as calling clearProduct().
     *
     * @param product The product to set as the current product.
     */
    public void setProduct(ProductEntity product) {
        observedProduct.set(product);
    }

    /**
     * Clears the current product.
     */
    public void clearProduct() {
        observedProduct.clear();
    }

    /**
     * Selects a product by ID and sets it as the current product.
     *
     * @param productId The ID of the product to select.
     * @return An Optional containing the selected product or empty if not found.
     */
    public Optional<ProductEntity> selectProduct(int productId) {
        observedProduct.set(productDAO.get(productId));
        return getProduct();
    }

    /**
     * Creates a new product in the database and sets it as the current product.
     * If the transaction fails, the current product is cleared.
     *
     * @param product The product to create.
     * @return An Optional containing the created product or empty if the transaction failed.
     */
    public Optional<ProductEntity> createAndSelectProduct(ProductEntity product) {
        try {
            productDAO.insert(product);
            return selectProduct(product.getId());
        } catch (DatabaseOperationException e) {
            errorHandler.onTransactionError(e.getMessage());
            clearProduct();
        }
        return getProduct();
    }

    /**
     * Updates the current product with the provided data.
     * If the transaction fails, the current product is cleared.
     *
     * @param product The ProductEntity containing the new data.
     * @throws IllegalStateException if no product has been selected.
     */
    public void updateProduct(ProductEntity product) throws IllegalStateException {
        try {
            var entity = getProduct().orElseThrow();
            entity.setAllBasic(product);
            productDAO.update(entity);
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("No product has been selected");
        } catch (DatabaseOperationException e) {
            errorHandler.onTransactionError(e.getMessage());
            clearProduct();
        }
    }

    /**
     * Deletes the current product from the database.
     * Whether the transaction fails or not, the current product is cleared.
     *
     * @throws IllegalStateException if no product has been selected.
     */
    public void deleteProduct() throws IllegalStateException {
        try {
            var entity = getProduct().orElseThrow();
            productDAO.delete(entity);
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("No product has been selected");
        } catch (DatabaseOperationException e) {
            errorHandler.onTransactionError(e.getMessage());
        } finally {
            clearProduct();
        }
    }

    /**
     * Retrieves a list of all products for a specific customer from the database.
     *
     * @param customerId The ID of the customer for which to retrieve the products.
     * @return A list of ProductEntity objects associated with the specified customer.
     */
    public List<ProductEntity> getProductsByCustomer(int customerId) {
        return productDAO.getAll(customerId);
    }
}
