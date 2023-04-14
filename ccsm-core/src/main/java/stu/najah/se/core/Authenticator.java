package stu.najah.se.core;

/**
 * <p>
 * Authenticator is an interface that should be implemented by classes
 * responsible for managing user session transitions (e.g., switching between
 * login and main screens). Its goal is to connect and initialize the
 * AdminService so that it receives login and logout when required.
 * </p><p>
 * An Authenticator implementation should initialize the AdminService
 * by calling the ServiceManager.initializeAdminService() method,
 * providing itself as the Authenticator instance.
 * </p>
 * Note that only one Authenticator can initialize the AdminService.
 */
public interface Authenticator {
    /**
     * Requests the main application to transition to the main scene
     * after a successful login.
     */
    void login();

    /**
     * Requests the main application to transition to the login scene
     * after a logout.
     */
    void logout();
}
