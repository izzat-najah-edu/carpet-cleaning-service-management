package stu.najah.se;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import stu.najah.se.gui.Launcher;

import java.sql.Connection;

/**
 * This is the main class where the application starts,
 * it includes a static method to get the database connection.
 * calling Navigator.main() will launch the application.
 */
public class Navigator {

    /**
     * @return the database connection
     */
    @NotNull
    public static Connection getConnection() {
        // to be implemented ...
        return null;
    }

    public static void main(String[] args) {
        // this line launches the application without blocking the control flow
        Platform.startup(() -> new Launcher().start(new Stage()));
        System.out.println("Hello World!");
    }

}
