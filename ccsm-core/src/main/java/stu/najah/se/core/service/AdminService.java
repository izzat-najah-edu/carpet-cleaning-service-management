package stu.najah.se.core.service;

import stu.najah.se.core.Authenticator;
import stu.najah.se.core.dao.AdminDAO;
import stu.najah.se.core.entity.AdminEntity;

/**
 * AdminService is a singleton class responsible for managing admin-related operations
 * such as authentication and user session management.
 */
public class AdminService {

    private static AdminService instance;

    private final AdminDAO adminDAO;

    private final Authenticator authenticator;

    /**
     * Holds the current logged-in admin.
     */
    private AdminEntity currentAdmin = null;

    /**
     * Creates a new AdminService instance
     * with the provided AdminDAO and Authenticator.
     *
     * @param adminDAO      the data access object for admin entities
     * @param authenticator the authenticator responsible for managing user sessions
     */
    private AdminService(AdminDAO adminDAO, Authenticator authenticator) {
        this.adminDAO = adminDAO;
        this.authenticator = authenticator;
    }

    /**
     * Initializes the singleton AdminService instance.
     *
     * @param adminDAO      the data access object for admin entities
     * @param authenticator the authenticator responsible for managing user sessions
     * @return the initialized AdminService instance
     * @throws IllegalStateException if the AdminService has already been initialized
     */
    public static AdminService initialize(AdminDAO adminDAO, Authenticator authenticator) throws IllegalStateException {
        if (instance == null) {
            return instance = new AdminService(adminDAO, authenticator);
        } else {
            throw new IllegalStateException("AdminService already initialized!");
        }
    }

    /**
     * Retrieves the singleton AdminService instance.
     *
     * @return the AdminService instance
     * @throws IllegalStateException if the AdminService has not been initialized
     */
    public static AdminService getInstance() throws IllegalStateException {
        if (instance == null) {
            throw new IllegalStateException("AdminService not initialized!");
        } else {
            return instance;
        }
    }

    /**
     * Retrieves the current logged-in admin.
     *
     * @return the current logged-in admin, or null if no admin is logged in
     */
    public AdminEntity getCurrentAdmin() {
        return currentAdmin;
    }

    /**
     * Checks if the user is logged in.
     *
     * @return true if the user is logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return currentAdmin != null;
    }

    /**
     * Logs out the current user and requests a logout for the authenticator.
     */
    public void logout() {
        currentAdmin = null;
        authenticator.logout();
    }

    /**
     * Logs in the given admin and requests a login for the authenticator.
     *
     * @param admin the admin entity to be logged in
     */
    private void login(AdminEntity admin) {
        currentAdmin = admin;
        authenticator.login();
    }

    /**
     * Attempts to authenticate the given username and password.
     * If the credentials are valid, the admin is logged in,
     * and a login is requested for the authenticator.
     *
     * @param username the username to be checked against the database
     * @param password the password to be checked against the database
     * @throws IllegalArgumentException if the provided credentials are invalid
     */
    public void authenticate(String username, String password) throws IllegalArgumentException {
        var admin = adminDAO.get(username);
        if (admin == null) {
            throw new IllegalArgumentException("Invalid username!");
        } else if (!admin.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password!");
        } else {
            login(admin);
        }
    }
}
