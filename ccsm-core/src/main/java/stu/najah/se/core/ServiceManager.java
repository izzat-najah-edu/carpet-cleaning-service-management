package stu.najah.se.core;

import stu.najah.se.core.dao.AdminDAO;
import stu.najah.se.core.dao.CustomerDAO;
import stu.najah.se.core.service.AdminService;
import stu.najah.se.core.service.CustomerService;

/**
 * Utility class responsible for managing services and their dependencies
 * within the application.
 */
public class ServiceManager {

    private ServiceManager() {
    }

    private static AdminService adminServiceInstance;

    private static CustomerService customerServiceInstance;

    /**
     * Initializes the AdminService with the provided Authenticator.
     * This method ensures that a single AdminService instance is shared between all users.
     *
     * @param authenticator the authenticator responsible for managing user sessions
     * @return an instance of the initialized AdminService
     * @throws IllegalStateException if the AdminService is already initialized
     */
    public static AdminService initializeAdminService(Authenticator authenticator)
            throws IllegalStateException {
        if (adminServiceInstance == null) {
            return adminServiceInstance = new AdminService(new AdminDAO(), authenticator);
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
     * Initializes the CustomerService with the provided DatabaseErrorListener.
     * This method ensures that a single CustomerService instance is shared between all users.
     *
     * @param errorListener the listener responsible for handling database errors
     * @return an instance of the initialized CustomerService
     * @throws IllegalStateException if the CustomerService is already initialized
     */
    public static CustomerService initializeCustomerService(DatabaseErrorListener errorListener)
            throws IllegalStateException {
        if (customerServiceInstance == null) {
            return customerServiceInstance = new CustomerService(new CustomerDAO(), errorListener);
        } else {
            throw new IllegalStateException("CustomerService has already been initialized!");
        }
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
}
