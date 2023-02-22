package stu.najah.se;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import stu.najah.se.gui.Launcher;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This is the main class where the application starts,
 * it includes a static method to get the database connection.
 * calling Navigator.main() will launch the application.
 */
public class Navigator {

    private static Connection connection;

    /**
     * @return the database connection
     */
    @NotNull
    public static Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
        // this establishes the connection with the database
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/carpet_cleaning_service_management",
                    "ccsm", "ccsm1234"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // this closes the connection after the application is shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }));
        // this launches the application without blocking the control flow
        Platform.startup(() -> {
            try {
                new Launcher().start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
