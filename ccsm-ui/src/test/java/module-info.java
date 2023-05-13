module ccsm.ui.test {
    requires ccsm.ui;
    requires ccsm.core;

    requires java.sql;

    requires javafx.controls;
    requires javafx.fxml;

    requires io.cucumber.java;
    requires io.cucumber.core;
    requires io.cucumber.junit;

    requires org.testfx;
    requires org.testfx.junit5;
    requires junit;

    exports stu.najah.se.test.ui.features.aya;
    exports stu.najah.se.test.ui.features.izzat;
}
