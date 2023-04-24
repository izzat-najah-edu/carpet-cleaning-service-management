module ccsm.ui {
    requires ccsm.core;

    requires javafx.controls;
    requires javafx.fxml;

    opens stu.najah.se.ui.control to javafx.fxml;
    opens stu.najah.se.ui to javafx.graphics;

    exports stu.najah.se.ui to ccsm.ui.test, org.testfx;
}
