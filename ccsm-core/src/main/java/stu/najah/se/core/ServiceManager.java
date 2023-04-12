package stu.najah.se.core;

import stu.najah.se.core.dao.AdminDAO;
import stu.najah.se.core.service.AdminService;

/**
 * ServiceManager is a utility class responsible for managing
 * the services and their dependencies within the application.
 */
public class ServiceManager {

    private ServiceManager() {
    }

    /**
     * Initializes the AdminService with the provided Authenticator.
     * This method ensures that the AdminService is properly set up with its dependencies.
     *
     * @param authenticator the authenticator responsible for managing user sessions
     * @return an instance of the initialized AdminService
     * @throws IllegalStateException if the AdminService is already initialized
     */
    public static AdminService initializeAdminService(Authenticator authenticator) throws IllegalStateException {
        return AdminService.initialize(new AdminDAO(), authenticator);
    }
}
