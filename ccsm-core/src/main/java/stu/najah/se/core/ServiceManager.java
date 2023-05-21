package stu.najah.se.core;

import stu.najah.se.core.dao.*;
import stu.najah.se.core.service.*;

/**
 * Utility class responsible for managing services and their dependencies
 * within the application.
 */
public class ServiceManager {

    private ServiceManager() {
    }

    private static AdminService adminServiceInstance;

    private static CustomerService customerServiceInstance;

    private static ProductService productServiceInstance;

    private static OrderService orderServiceInstance;

    private static OrderProductService orderProductServiceInstance;

    /**
     * Initializes the AdminService.
     * This method ensures that a single AdminService instance is shared between all users.
     *
     * @throws IllegalStateException if the AdminService is already initialized
     */
    public static void initializeAdminService()
            throws IllegalStateException {
        if (adminServiceInstance == null) {
            adminServiceInstance = new AdminService(new AdminDAO());
        } else {
            throw new IllegalStateException("AdminService has already been initialized!");
        }
    }

    /**
     * Retrieves the shared instance of the AdminService.
     *
     * @return the AdminService instance
     * @throws IllegalStateException if the AdminService has not been initialized
     */
    public static AdminService getAdminService() throws IllegalStateException {
        if (adminServiceInstance == null) {
            throw new IllegalStateException("AdminService has not been initialized!");
        } else {
            return adminServiceInstance;
        }
    }

    /**
     * Initializes the CustomerService and the ProductService with the provided DatabaseErrorListener.
     * This method ensures that a single service instances are shared between all users.
     *
     * @param errorListener        the listener responsible for handling database errors
     * @param confirmationListener the listener responsible for handling email confirmations
     * @throws IllegalStateException if the services are already initialized
     */
    public static void initializeEntityServices(
            DatabaseErrorListener errorListener,
            EmailConfirmationListener confirmationListener)
            throws IllegalStateException, SecurityException {
        if (customerServiceInstance != null) {
            throw new IllegalStateException("Services have already been initialized!");
        }
        customerServiceInstance = new CustomerService(new CustomerDAO(), errorListener);
        productServiceInstance = new ProductService(new ProductDAO(), errorListener, customerServiceInstance);
        orderServiceInstance = new OrderService(
                new OrderDAO(), new OrderProductDAO(), errorListener, confirmationListener, customerServiceInstance
        );
        orderProductServiceInstance = new OrderProductService(
                new OrderProductDAO(), errorListener, orderServiceInstance, productServiceInstance
        );
    }

    /**
     * Retrieves the shared instance of the CustomerService.
     *
     * @return the CustomerService instance
     * @throws IllegalStateException if the CustomerService has not been initialized
     */
    public static CustomerService getCustomerService() throws IllegalStateException {
        if (customerServiceInstance == null) {
            throw new IllegalStateException("CustomerService has not been initialized!");
        } else {
            return customerServiceInstance;
        }
    }

    /**
     * Retrieves the shared instance of the ProductService.
     *
     * @return the ProductService instance
     * @throws IllegalStateException if the ProductService has not been initialized
     */
    public static ProductService getProductService() throws IllegalStateException {
        if (productServiceInstance == null) {
            throw new IllegalStateException("ProductService has not been initialized!");
        } else {
            return productServiceInstance;
        }
    }

    /**
     * Retrieves the shared instance of the OrderService.
     *
     * @return the OrderService instance
     * @throws IllegalStateException if the OrderService has not been initialized
     */
    public static OrderService getOrderService() throws IllegalStateException {
        if (orderServiceInstance == null) {
            throw new IllegalStateException("OrderService has not been initialized!");
        } else {
            return orderServiceInstance;
        }
    }

    /**
     * Retrieves the shared instance of the OrderProductService.
     *
     * @return the OrderProductService instance
     * @throws IllegalStateException if the OrderProductService has not been initialized
     */
    public static OrderProductService getOrderProductService() throws IllegalStateException {
        if (orderProductServiceInstance == null) {
            throw new IllegalStateException("OrderProductService has not been initialized!");
        } else {
            return orderProductServiceInstance;
        }
    }
}
