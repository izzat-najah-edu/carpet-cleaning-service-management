package stu.najah.se.core;

import stu.najah.se.core.dao.AdminDAO;
import stu.najah.se.core.service.AdminService;

/**
 * Utility class responsible for managing services and their dependencies
 * within the application.
 */
public class ServiceManager {

    private ServiceManager() {
    }

    private static AdminService adminServiceInstance;

    /**
     * Initializes the AdminService with the provided Authenticator.
     * This method ensures that a single AdminService instance is shared between all users.
     *
     * @param authenticator the authenticator responsible for managing user sessions
     * @return an instance of the initialized AdminService
     * @throws IllegalStateException if the AdminService is already initialized
     */
    public static AdminService initializeAdminService(Authenticator authenticator) throws IllegalStateException {
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
}
