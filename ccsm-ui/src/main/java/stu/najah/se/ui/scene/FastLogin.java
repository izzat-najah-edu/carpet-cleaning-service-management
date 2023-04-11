package stu.najah.se.ui.scene;

import javafx.application.Platform;
import javafx.stage.Stage;
import stu.najah.se.core.ServicesManager;

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
        Platform.runLater(() ->
                ServicesManager.getAdminService().authenticate("admin", "admin"));
    }
}
