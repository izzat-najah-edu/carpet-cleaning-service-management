package stu.najah.se.core;

/**
 * An interface for handling authentication-related actions in the application.
 * Implementations of this interface should handle the transition between
 * logged-in and logged-out states, such as displaying the appropriate UI
 * components and managing user session data.
 */
public interface Authenticator {

    /**
     * Request to transition the application to the logged-in state.
     * Implementations should handle the necessary changes in the application
     * state and UI to reflect that a user has successfully logged in.
     */
    void requestLogin();

    /**
     * Request to transition the application to the logged-out state.
     * Implementations should handle the necessary changes in the application
     * state and UI to reflect that a user has logged out, and prompt the user
     * to provide login credentials if needed.
     */
    void requestLogout();
}
