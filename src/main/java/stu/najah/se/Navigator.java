package stu.najah.se;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import stu.najah.se.gui.PromptManager;
import stu.najah.se.gui.SceneManager;

import java.io.IOException;

/**
 * This is the main class where the application starts.
 * it includes a static method to generate database sessions.
 * it includes different manager objects to control the application.
 * calling Navigator.main() will not stop the control flow.
 */
public class Navigator {

    /**
     * The gui controller, to move between scenes
     */
    private static final SceneManager sceneManager = new SceneManager();
    /**
     * The prompt controller, to show different alerts
     */
    private static final PromptManager promptManager = new PromptManager();
    /**
     * The database connector object, to generate the sessions
     */
    private static SessionFactory sessionFactory;

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
                sceneManager.start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * To shut down the application completely.
     */
    public static void exit() {
        Platform.exit();
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
     * Generates a new session object.
     * Always close the session after, and don't keep references to it.
     * The session is meant to be disregarded as soon as the transaction is finished
     *
     * @return a new session object
     */
    public static Session createSession() throws HibernateException {
        return sessionFactory.openSession();
    }

}
