package stu.najah.se.scene;

import javafx.application.Platform;
import javafx.stage.Stage;

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
