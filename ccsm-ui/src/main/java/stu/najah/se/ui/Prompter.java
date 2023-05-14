package stu.najah.se.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import stu.najah.se.core.DatabaseErrorListener;
import stu.najah.se.core.EmailConfirmationListener;

/**
 * Utility class for (graphically) prompting alerts, inputs texts, dialogs, etc...
 */
public abstract class Prompter
        implements DatabaseErrorListener, EmailConfirmationListener {

    private static final Prompter adapterListener = new Prompter() {
        @Override
        public void onTransactionError(String message) {
            Prompter.error(message);
        }

        @Override
        public boolean onEmailConfirmation(String message) {
            return Prompter.confirm(message);
        }

        @Override
        public void onEmailSent(String message) {
            Prompter.info(message);
        }
    };

    public static Prompter getListener() {
        return adapterListener;
    }

    private Prompter() {
    }

    /**
     * Displays a message alert, with no interaction options
     *
     * @param message to be displayed
     * @param type    only use INFORMATION, WARNING, or ERROR
     *                because they don't have multiple buttons
     */
    private static void prompt(String message, Alert.AlertType type) {
        var alert = new Alert(type);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Displays a confirmation dialog, with OK and Cancel buttons
     *
     * @param message to be displayed
     * @return true if OK was pressed, false otherwise
     */
    public static boolean confirm(String message) {
        var alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setContentText(message);
        var result = alert.showAndWait();
        return result.isPresent() && result.get().equals(ButtonType.OK);
    }

    /**
     * Displays an information message on screen
     *
     * @param message to be displayed
     */
    public static void info(String message) {
        prompt(message, Alert.AlertType.INFORMATION);
    }

    /**
     * Displays a warning message on screen
     *
     * @param message to be displayed
     */
    public static void warning(String message) {
        prompt(message, Alert.AlertType.WARNING);
    }

    /**
     * Displays an error message on screen
     *
     * @param message to be displayed
     */
    public static void error(String message) {
        prompt(message, Alert.AlertType.ERROR);
    }

    /**
     * Displays an error message on screen
     *
     * @param e it's message will be displayed
     */
    public static void error(Exception e) {
        prompt(e.getMessage(), Alert.AlertType.ERROR);
    }


    public static void loginError() {
        error("Login failed. Please check your username and password and try again.");
    }


}
