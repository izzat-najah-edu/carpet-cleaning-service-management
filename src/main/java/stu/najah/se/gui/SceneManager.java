package stu.najah.se.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import stu.najah.se.Navigator;
import stu.najah.se.gui.fxml.LoginController;
import stu.najah.se.gui.fxml.MainController;
import stu.najah.se.sql.dao.AdminDAO;
import stu.najah.se.sql.entity.AdminEntity;

import java.io.IOException;

/**
 * This is the JavaFX application launcher.
 * it controls the scenes of the main stage.
 * it controls the login authorization.
 * the initial scene of the stage is the login scene.
 */
public class SceneManager extends Application {

    private Stage stage;
    private boolean loggedIn = false;

    private LoginController loginController;
    private MainController mainController;

    private Scene loginScene;
    private Scene mainScene;

    private final AdminDAO adminDAO = new AdminDAO();
    private AdminEntity currentAdmin = null;

    @Override
    public void start(Stage stage) throws IOException {
        // the stage reference
        // MUST REFERENCE BEFORE INITIALIZING THE CONTROLLERS
        this.stage = stage;
        // login scene
        var loader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));
        this.loginScene = new Scene(loader.load());
        this.loginController = loader.getController();
        this.loginScene.getStylesheets().add(
                String.valueOf(getClass().getResource("style/main.css")));
        // main scene
        loader = new FXMLLoader(getClass().getResource("fxml/main.fxml"));
        this.mainScene = new Scene(loader.load());
        this.mainController = loader.getController();
        this.mainScene.getStylesheets().add(
                String.valueOf(getClass().getResource("style/main.css")));
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
    public Stage getStage() {
        return stage;
    }

    /**
     * Switches the scene of the application to the login scene.
     * if the login scene is already displayed nothing happens.
     */
    private void setLoginScene() {
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
    private void setMainScene() {
        if (loggedIn) {
            return;
        }
        stage.setScene(mainScene);
        stage.centerOnScreen();
        loginController.reset();
        loggedIn = true;
    }

    /**
     * @return current logged in admin, or null if the application is logged out
     */
    public AdminEntity getCurrentAdmin() {
        return currentAdmin;
    }

    /**
     * @return whether the user has logged in to the main scene or not
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Resets the admins, and logs out to the login screen
     */
    public void logout() {
        currentAdmin = null;
        setLoginScene();
    }

    /**
     * Tries to authorize the given user information.
     * If valid it the admin will be cashed and the application will log in
     * @param username to be checked in the database
     * @param password to be checked in the database
     */
    public void login(String username, String password) {
        var admin = adminDAO.get(username);
        if(admin != null && admin.getPassword().equals(password)) {
            // the username exists, and the given password is correct
            currentAdmin = admin;
            setMainScene();
        } else {
            Navigator.getPromptManager().warning("Invalid username or password!");
        }
    }

}
