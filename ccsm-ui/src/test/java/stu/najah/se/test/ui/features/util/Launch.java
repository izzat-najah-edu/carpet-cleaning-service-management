package stu.najah.se.test.ui.features.util;

import javafx.application.Platform;
import javafx.stage.Stage;
import stu.najah.se.core.ServiceManager;
import stu.najah.se.ui.Prompter;
import stu.najah.se.ui.SceneManager;

public class Launch {

    public static void main(String[] args) {
        runAndSkipLogin();
    }

    public static void run() {
        Platform.startup(() -> {
            try {
                ServiceManager.initializeEntityServices(Prompter.getInstance());
                var sceneManager = new SceneManager();
                sceneManager.init();
                sceneManager.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void skipLogin() {
        Platform.runLater(() ->
                ServiceManager.getAdminService().authenticate("admin", "admin"));
    }

    public static void runAndSkipLogin() {
        run();
        skipLogin();
    }
}
