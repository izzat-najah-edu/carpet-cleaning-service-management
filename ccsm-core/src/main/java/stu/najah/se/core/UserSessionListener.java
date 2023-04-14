package stu.najah.se.core;

/**
 * <p>
 * UserSessionListener is an interface that should be implemented by classes
 * responsible for managing user session transitions (e.g., switching between
 * login and main screens). Its goal is to connect and initialize the
 * AdminService so that it receives login and logout when required.
 * </p><p>
 * A UserSessionListener implementation should initialize the AdminService
 * by calling the ServiceManager.initializeAdminService() method,
 * providing itself as the UserSessionListener instance.
 * </p>
 * Note that only one UserSessionListener can initialize the AdminService.
 */
public interface UserSessionListener {
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
