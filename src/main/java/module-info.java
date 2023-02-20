module CarpetCleaningServiceManagement {
    requires org.jetbrains.annotations;
    requires java.sql;

    requires javafx.controls;
    requires javafx.fxml;

    opens stu.najah.se.gui to javafx.fxml;
    exports stu.najah.se.gui;

    // for testing
    exports stu.najah.se;
}
