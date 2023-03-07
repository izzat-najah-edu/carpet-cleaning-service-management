package stu.najah.se;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import stu.najah.se.gui.FXUtility;
import stu.najah.se.gui.PromptManager;
import stu.najah.se.gui.SceneManager;
import stu.najah.se.sql.dao.AdminDAO;
import stu.najah.se.sql.entity.AdminEntity;

import java.io.IOException;

/**
 * This is the main class where the application starts,
 * it includes a static method to generate database sessions.
 * it includes a static method to display prompt messages to the user.
 * it includes some static methods to control the application (e.g. logout, login, exit)
 * calling Navigator.main() will launch the application.
 */
public class Navigator {

    /**
     * The factory object that generates the sessions
     */
    private static SessionFactory sessionFactory;
    /**
     * The gui controller, to move between scenes
     */
    private static SceneManager sceneManager;
    /**
     * The prompt controller, to show different alerts
     */
    private static final PromptManager promptManager = new PromptManager();
    /**
     * The admin data access object
     */
    private static final AdminDAO adminDAO = new AdminDAO();
    /**
     * A detached object of the admin that is logged in the system
     */
    private static AdminEntity currentAdmin;

    public static void main(String[] args) {
        // initialize the session factory
        try {
            var configuration = new Configuration().configure(
                    Navigator.class.getResource("hibernate.cfg.xml"));
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
        // close the session factory after the application is shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(sessionFactory::close));
        // launch the application without blocking the control flow
        Platform.startup(() -> {
            try {
                sceneManager = new SceneManager();
                sceneManager.start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Always close the session after, and don't keep references to it.
     * it's meant to be disregarded as soon as the transaction is finished
     *
     * @return a new session object
     */
    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    /**
     * @return the scene manager of the application
     */
    public static SceneManager getSceneManager() {
        return sceneManager;
    }

    /**
     * @return the prompt manager of the application
     */
    public static PromptManager getPromptManager() {
        return promptManager;
    }

    /**
     * This entity is a detached object
     *
     * @return the current admin if logged in. otherwise null
     */
    public static AdminEntity getCurrentAdmin() {
        return currentAdmin;
    }

    /**
     * To shut down the application completely.
     */
    public static void exit() {
        Platform.exit();
    }

    /**
     * Logs out of the main screen to the login panel.
     * Or does nothing if the login panel is already displayed.
     * The current admin will be set to null.
     */
    public static void logout() {
        currentAdmin = null;
        sceneManager.setLoginScene();
    }

    /**
     * Tries to logs into the main screen from the login panel using the given user information.
     * If it fails a prompt is displayed. check .isLoggedIn() to test the result
     *
     * @param username will be checked in the database.
     * @param password will be checked in the database.
     */
    public static void login(String username, String password) {
        var admin = adminDAO.get(username);
        if (admin != null && admin.getPassword().equals(password)) {
            // the username exists, and the given password is correct
            Navigator.currentAdmin = admin;
            sceneManager.setMainScene();
        } else {
            promptManager.warning("Invalid username or password!");
        }
    }

    /**
     * @return whether the user is logged in or not
     */
    public static boolean isLoggedIn() {
        return sceneManager.isLoggedIn();
    }

}
