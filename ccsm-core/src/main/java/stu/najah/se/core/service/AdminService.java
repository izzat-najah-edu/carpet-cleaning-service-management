package stu.najah.se.core.service;

import stu.najah.se.core.Authenticator;
import stu.najah.se.core.dao.AdminDAO;
import stu.najah.se.core.entity.AdminEntity;

/**
 * AdminService is responsible for managing admin-related operations
 * such as authentication and user session management.
 */
public class AdminService {

    private final AdminDAO adminDAO;

    private final Authenticator authenticator;

    /**
     * Holds the current logged-in admin
     */
    private AdminEntity currentAdmin = null;

    /**
     * Constructs a new AdminService instance with the provided AdminDAO and Authenticator.
     *
     * @param adminDAO      the data access object for admin entities
     * @param authenticator the authenticator responsible for managing user sessions
     */
    public AdminService(AdminDAO adminDAO, Authenticator authenticator) {
        this.adminDAO = adminDAO;
        this.authenticator = authenticator;
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
     * Logs out the current user and requests a transition to the login screen.
     */
    public void logout() {
        currentAdmin = null;
        authenticator.requestLogout();
    }

    /**
     * Logs in the given admin and requests a transition to the main scene.
     *
     * @param admin the admin entity to be logged in
     */
    private void login(AdminEntity admin) {
        currentAdmin = admin;
        authenticator.requestLogin();
    }

    /**
     * Attempts to authenticate the given username and password.
     * If the credentials are valid, the admin is logged in and a transition is requested.
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
