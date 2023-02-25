package stu.najah.se.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * This is the JavaFX application launcher.
 */
public class Launcher extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader controller = new FXMLLoader(Objects.requireNonNull(
                getClass().getResource("fxml/login.fxml")));
        Parent root = controller.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
