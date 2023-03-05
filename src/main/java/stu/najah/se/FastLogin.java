package stu.najah.se;

import javafx.application.Platform;

/**
 * TEST CLASS
 */
public class FastLogin {
    public static void main(String[] args) {
        Navigator.main(new String[]{});
        Platform.runLater(() -> Navigator.login("admin", "admin"));
    }
}
