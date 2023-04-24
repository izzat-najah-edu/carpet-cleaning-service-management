module ccsm.ui.test {
    requires ccsm.ui;
    requires ccsm.core;

    requires javafx.controls;
    requires javafx.fxml;

    requires io.cucumber.java;
    requires io.cucumber.core;
    requires io.cucumber.junit;

    requires junit;
    requires org.hamcrest;
    requires org.testfx;
    requires org.testfx.junit5;

    exports stu.najah.se.test.ui.features.aya;
    exports stu.najah.se.test.ui.features.izzat;
}
