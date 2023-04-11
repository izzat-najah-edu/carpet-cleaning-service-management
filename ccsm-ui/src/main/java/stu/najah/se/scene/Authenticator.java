package stu.najah.se.scene;

import stu.najah.se.Prompter;
import stu.najah.se.dao.AdminDAO;
import stu.najah.se.entity.AdminEntity;

/**
 * Utility class to control the login authorization.
 */
public class Authenticator {

    /**
     * Admin entity data access object
     */
    private static final AdminDAO adminDAO = new AdminDAO();
    /**
     * The user that is currently logged in
     */
    private static AdminEntity currentAdmin = null;

    /**
     * @return current logged in admin, or null if the application is logged out
     */
    public static AdminEntity getCurrentAdmin() {
        return currentAdmin;
    }

    /**
     * @return whether the user has logged in to the main scene or not
     */
    public static boolean isLoggedIn() {
        return currentAdmin != null;
    }

    /**
     * Resets the admins, and logs out to the login screen.
     */
    public static void logout() {
        currentAdmin = null;
        SceneManager.getInstance().setLoginScene();
    }

    /**
     * Tries to authorize the given user information.
     * If it's valid the admin will be stored and the application will log in.
     * Otherwise, a warning is prompter.
     * No further indicators, Check isLoggedIn() to test the result.
     *
     * @param username to be checked in the database
     * @param password to be checked in the database
     */
    public static void login(String username, String password) {
        var admin = adminDAO.get(username);
        if (admin != null && admin.getPassword().equals(password)) {
            // the username exists and the given password is correct
            currentAdmin = admin;
            SceneManager.getInstance().setMainScene();
        } else {
            Prompter.warning("Invalid username or password!");
        }
    }

}
