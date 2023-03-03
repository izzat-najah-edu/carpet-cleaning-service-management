module CarpetCleaningServiceManagement {
    requires org.jetbrains.annotations;

    requires java.sql;
    requires java.naming;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    opens stu.najah.se.sql.entity to org.hibernate.orm.core;

    requires javafx.controls;
    requires javafx.fxml;
    opens stu.najah.se.gui to javafx.fxml;
    opens stu.najah.se.gui.fxml to javafx.fxml;

    // for testing
    exports stu.najah.se;
    exports stu.najah.se.gui;
    exports stu.najah.se.gui.fxml;
    exports stu.najah.se.sql.entity;
}
