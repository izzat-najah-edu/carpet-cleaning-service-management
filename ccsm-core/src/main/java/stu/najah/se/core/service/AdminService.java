package stu.najah.se.core.service;

import stu.najah.se.core.UserSessionListener;
import stu.najah.se.core.dao.AdminDAO;
import stu.najah.se.core.entity.AdminEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * AdminService is a class responsible for managing admin-related operations
 * such as authentication and user session management.
 */
public class AdminService {

    private final AdminDAO adminDAO;

    private final List<UserSessionListener> sessionListener = new ArrayList<>();

    /**
     * Holds the current logged-in admin.
     */
    private AdminEntity currentAdmin = null;

    /**
     * Creates a new AdminService instance
     * with the provided AdminDAO.
     *
     * @param adminDAO the data access object for admin entities
     */
    public AdminService(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    /**
     * Subscribes a listener to be notified of user session changes.
     *
     * @param listener The UserSessionListener to subscribe for notifications.
     */
    public void subscribe(UserSessionListener listener) {
        sessionListener.add(listener);
    }

    /**
     * Unsubscribes a listener from receiving notifications about user session changes.
     *
     * @param listener The UserSessionListener to unsubscribe from notifications.
     */
    public void unsubscribe(UserSessionListener listener) {
        sessionListener.remove(listener);
    }

    /**
     * Retrieves the current logged-in admin as an Optional.
     *
     * @return an Optional containing the current logged-in admin.
     * the Optional is empty if no admin is logged in
     */
    public Optional<AdminEntity> getCurrentAdmin() {
        return Optional.ofNullable(currentAdmin);
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
        sessionListener.forEach(UserSessionListener::logout);
    }

    /**
     * Logs in the given admin and requests a login for the authenticator.
     *
     * @param admin the admin entity to be logged in
     */
    private void login(AdminEntity admin) {
        currentAdmin = admin;
        sessionListener.forEach(UserSessionListener::login);
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
