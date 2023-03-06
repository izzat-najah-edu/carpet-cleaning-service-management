package stu.najah.se.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jetbrains.annotations.NotNull;
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
    private boolean loggedIn = false;

    private LoginController loginController;
    private MainController mainController;

    private Scene loginScene;
    private Scene mainScene;

    @Override
    public void start(Stage stage) throws IOException {
        // the stage reference
        // MUST REFERENCE BEFORE INITIALIZING THE CONTROLLERS
        this.stage = stage;
        // login scene
        var loader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));
        this.loginScene = new Scene(loader.load());
        this.loginController = loader.getController();
        // main scene
        loader = new FXMLLoader(getClass().getResource("fxml/main.fxml"));
        this.mainScene = new Scene(loader.load());
        this.mainController = loader.getController();
        // the stage
        this.stage.initStyle(StageStyle.TRANSPARENT);
        this.stage.setTitle("Carpet Cleaning Service Management");
        this.stage.setScene(loginScene);
        this.stage.centerOnScreen();
        this.stage.show();
    }

    /**
     * @return the primary stage of the application
     */
    @NotNull
    public Stage getStage() {
        return stage;
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
        stage.centerOnScreen();
        mainController.reset();
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
        stage.centerOnScreen();
        loginController.reset();
        loggedIn = true;
    }

    /**
     * @return whether the user has logged in to the main scene or not
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }


}
