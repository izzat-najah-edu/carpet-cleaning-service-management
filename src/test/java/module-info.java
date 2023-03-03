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
    requires org.hamcrest;
    requires org.testfx.junit5;

    opens main to org.junit.platform.commons;
    exports main;
    exports features;
}
