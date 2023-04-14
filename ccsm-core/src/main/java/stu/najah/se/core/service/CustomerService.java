package stu.najah.se.core.service;

import stu.najah.se.core.DatabaseErrorListener;
import stu.najah.se.core.DatabaseOperationException;
import stu.najah.se.core.EntityListener;
import stu.najah.se.core.dao.CustomerDAO;
import stu.najah.se.core.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Provides services for managing customer data.
 * It interacts with the database to perform CRUD operations and handles database transactions.
 * </p><p>
 * The class maintains a reference to a specific customer, which is set or cleared during various operations.
 * When an operation fails due to a database error, the current customer is cleared.
 * </p><p>
 * DatabaseErrorHandler is required for handling and logging database errors,
 * any error that occurs during transactions will be sent to the handler to deal with.
 * </p>
 */
public class CustomerService {

    private final CustomerDAO customerDAO;

    private final DatabaseErrorListener errorHandler;

    private final ObservedEntity<CustomerEntity> observedCustomer = new ObservedEntity<>();

    /**
     * Constructs a new CustomerService instance.
     *
     * @param customerDAO   The data access object to interact with the database.
     * @param errorListener The handler for database errors.
     */
    public CustomerService(CustomerDAO customerDAO, DatabaseErrorListener errorListener) {
        this.customerDAO = customerDAO;
        this.errorHandler = errorListener;
    }

    /**
     * Subscribes a listener to be notified of changes or clearing of the customer.
     *
     * @param listener The EntityListener to subscribe for notifications.
     */
    public void subscribe(EntityListener<CustomerEntity> listener) {
        observedCustomer.addListener(listener);
    }

    /**
     * Unsubscribes a listener from receiving notifications about changes or clearing of the customer.
     *
     * @param listener The EntityListener to unsubscribe from notifications.
     */
    public void unsubscribe(EntityListener<CustomerEntity> listener) {
        observedCustomer.removeListener(listener);
    }

    /**
     * Retrieves the current customer.
     *
     * @return An Optional containing the current customer or empty if no customer is set.
     */
    public Optional<CustomerEntity> getCustomer() {
        return observedCustomer.get();
    }

    /**
     * Sets the current customer.
     * providing null value has the same effect as calling clearCustomer().
     *
     * @param customer The customer to set as the current customer.
     */
    public void setCustomer(CustomerEntity customer) {
        observedCustomer.set(customer);
    }

    /**
     * Clears the current customer.
     */
    public void clearCustomer() {
        observedCustomer.clear();
    }

    /**
     * Selects a customer by ID and sets it as the current customer.
     *
     * @param customerId The ID of the customer to select.
     * @return An Optional containing the selected customer or empty if not found.
     */
    public Optional<CustomerEntity> selectCustomer(int customerId) {
        observedCustomer.set(customerDAO.get(customerId));
        return getCustomer();
    }

    /**
     * Selects a customer by name and sets it as the current customer.
     *
     * @param customerName The name of the customer to select.
     * @return An Optional containing the selected customer or empty if not found.
     */
    public Optional<CustomerEntity> selectCustomer(String customerName) {
        observedCustomer.set(customerDAO.get(customerName));
        return getCustomer();
    }

    /**
     * Selects a customer based on the provided CustomerEntity and sets it as the current customer.
     * The search is done using the name of the customer not the ID,
     * because the ID might not always be known, unlike the name which is also unique.
     *
     * @param customer The CustomerEntity to match the customer by name.
     * @return An Optional containing the selected customer or empty if not found.
     */
    public Optional<CustomerEntity> selectCustomer(CustomerEntity customer) {
        return selectCustomer(customer != null ? customer.getName() : null);
    }

    /**
     * Creates a new customer in the database and sets it as the current customer.
     * If the transaction fails, the current customer is cleared.
     *
     * @param customer The customer to create.
     * @return An Optional containing the created customer or empty if the transaction failed.
     */
    public Optional<CustomerEntity> createAndSelectCustomer(CustomerEntity customer) {
        try {
            customerDAO.insert(customer);
            return selectCustomer(customer);
        } catch (DatabaseOperationException e) {
            errorHandler.onTransactionError(e.getMessage());
            clearCustomer();
        }
        return getCustomer();
    }

    /**
     * Updates the current customer with the provided data.
     * If the transaction fails, the current customer is cleared.
     *
     * @param customer The CustomerEntity containing the new data.
     * @throws IllegalStateException if no customer has been selected.
     */
    public void updateCustomer(CustomerEntity customer) throws IllegalStateException {
        var optional = getCustomer();
        if (optional.isEmpty()) {
            throw new IllegalStateException("No customer has been selected");
        }
        var entity = optional.get();
        entity.setAllBasic(customer);
        try {
            customerDAO.update(entity);
        } catch (DatabaseOperationException e) {
            errorHandler.onTransactionError(e.getMessage());
            clearCustomer();
        }
    }

    /**
     * Deletes the current customer from the database.
     * Whether the transaction fails or not, the current customer is cleared.
     *
     * @throws IllegalStateException if no customer has been selected.
     */
    public void deleteCustomer() throws IllegalStateException {
        var optional = getCustomer();
        if (optional.isEmpty()) {
            throw new IllegalStateException("No customer has been selected");
        }
        try {
            customerDAO.delete(optional.get());
        } catch (DatabaseOperationException e) {
            errorHandler.onTransactionError(e.getMessage());
        } finally {
            clearCustomer();
        }
    }

    /**
     * Retrieves a list of all customers from the database.
     *
     * @return A list of CustomerEntity objects.
     */
    public List<CustomerEntity> getAllCustomers() {
        return customerDAO.getAll();
    }

    /**
     * Retrieves a list of customers from the database whose names contain the specified substring.
     *
     * @param nameSubstring The substring to filter customers by name.
     * @return A list of CustomerEntity objects with names containing the specified substring.
     */
    public List<CustomerEntity> getAllCustomersWith(String nameSubstring) {
        return customerDAO.getAllLike(nameSubstring);
    }

}
