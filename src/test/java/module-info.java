module CarpetCleaningServiceManagementTest {
    requires CarpetCleaningServiceManagement;

    requires io.cucumber.java;
    requires io.cucumber.core;
    requires io.cucumber.junit;

    requires junit;
    requires org.junit.jupiter.api;

    requires java.sql;

    requires javafx.controls;
    requires javafx.fxml;

    requires org.testfx;
    requires org.testfx.junit5;

    exports features;
    exports main;
}
