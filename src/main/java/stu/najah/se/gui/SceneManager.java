package stu.najah.se.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import stu.najah.se.gui.fxml.LoginController;
import stu.najah.se.gui.fxml.MainController;

import java.io.IOException;

/**
 * This is the JavaFX application launcher.
 * it controls the scenes of the main stage.
 * the initial scene of the stage is the login scene.
 */
public class SceneManager extends Application {

    private Stage stage;
    private Scene loginScene;
    private Scene mainScene;
    private LoginController loginController;
    private MainController mainController;
    private boolean loggedIn = false;

    @Override
    public void start(Stage stage) throws IOException {
        // login scene
        var loader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));
        this.loginScene = new Scene(loader.load());
        this.loginController = loader.getController();
        // main scene
        loader = new FXMLLoader(getClass().getResource("fxml/main.fxml"));
        this.mainScene = new Scene(loader.load());
        this.mainController = loader.getController();
        // the stage
        this.stage = stage;
        this.stage.setScene(loginScene);
        this.stage.show();
    }

    /**
     * Switches the scene of the application to the login scene.
     * if the login scene is already displayed nothing happens.
     */
    public void setLoginScene() {
        if (!loggedIn) {
            return;
        }
        stage.setScene(loginScene);
        mainController.clear();
        loggedIn = false;
    }

    /**
     * Switches the scene of the application to the main scene.
     * if the main scene is already displayed nothing happens.
     */
    public void setMainScene() {
        if (loggedIn) {
            return;
        }
        stage.setScene(mainScene);
        loginController.clear();
        loggedIn = true;
    }

    /**
     * @return whether the user has logged in to the main scene or not
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }
}
