package stu.najah.se;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import stu.najah.se.gui.SceneManager;
import stu.najah.se.sql.QueryManager;
import stu.najah.se.sql.entity.Admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This is the main class where the application starts,
 * it includes a static method to get the database connection.
 * it includes a static method to display prompt messages to the user.
 * it includes some static methods to control the application (e.g. logout, login, exit)
 * calling Navigator.main() will launch the application.
 */
public class Navigator {

    private static Connection connection;
    private static SceneManager sceneManager;
    private static QueryManager queryManager;
    private static Admin currentAdmin;

    public static void main(String[] args) {
        // this establishes the connection with the database
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/carpet_cleaning_service_management",
                    "ccsm", "ccsm1234"
            );
            connection.setAutoCommit(false);
            queryManager = new QueryManager(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // this closes the connection after the application is shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }));
        // this launches the application without blocking the control flow
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
     * @return the database connection
     */
    @NotNull
    public static Connection getConnection() {
        return connection;
    }

    /**
     * @return the scene manager of the application
     */
    public static SceneManager getSceneManager() {
        return sceneManager;
    }

    /**
     * @return the query manager of the application
     */
    public static QueryManager getQueryManager() {
        return queryManager;
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
     * Tries to logs into the main screen from the login panel using the given admin information.
     * If it fails nothing happens, check .isLoggedIn() to test the result
     * @param admin will be checked in the database.
     */
    public static void login(Admin admin) {
        if (queryManager.checkAdminExists(admin)) {
            Navigator.currentAdmin = admin;
            sceneManager.setMainScene();
        }
    }

    /**
     * @return whether the user is logged in or not
     */
    public static boolean isLoggedIn() {
        return sceneManager.isLoggedIn();
    }
}
