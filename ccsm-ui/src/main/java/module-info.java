module ccsm.ui {
    requires ccsm.core;

    requires javafx.controls;
    requires javafx.fxml;

    opens stu.najah.se.control to javafx.fxml;
    opens stu.najah.se.scene to javafx.graphics;
}
