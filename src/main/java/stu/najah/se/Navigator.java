package stu.najah.se;

import javafx.application.Platform;
import javafx.stage.Stage;
import stu.najah.se.gui.Launcher;
import stu.najah.se.sql.Database;

import java.io.IOException;

/**
 * This is the main class where the application starts.
 * calling Navigator.main() will not stop the control flow.
 */
public final class Navigator {

    private Navigator() {
    }

    /**
     * The gui controller, to move between scenes
     */
    private static final Launcher LAUNCHER = new Launcher();

    public static void main(String[] args) {
        // initialize the database
        Database.initialize();
        // launch the application without blocking the control flow
        Platform.startup(() -> {
            try {
                LAUNCHER.start(new Stage());
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
    public static Launcher getSceneManager() {
        return LAUNCHER;
    }

}
