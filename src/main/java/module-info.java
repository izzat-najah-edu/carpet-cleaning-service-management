module CarpetCleaningServiceManagement {
    requires org.jetbrains.annotations;
    requires java.sql;

    requires javafx.controls;
    requires javafx.fxml;

    opens stu.najah.se.gui to javafx.fxml;
    opens stu.najah.se.gui.fxml to javafx.fxml;
    exports stu.najah.se.gui;
    exports stu.najah.se.gui.fxml;

    // for testing
    exports stu.najah.se;
    exports stu.najah.se.sql;
    exports stu.najah.se.sql.entity;
}
