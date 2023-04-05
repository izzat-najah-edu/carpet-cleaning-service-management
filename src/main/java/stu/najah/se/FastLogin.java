package stu.najah.se;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import stu.najah.se.gui.scene.Authenticator;
import stu.najah.se.gui.scene.SceneManager;

/**
 * TEST CLASS
 */
public class FastLogin {
    public static void main(String[] args) {
        Platform.startup(() -> {
            try {
                var sceneManager = new SceneManager();
                sceneManager.init();
                sceneManager.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Platform.runLater(() -> Authenticator.login("admin", "admin"));
    }
}
