module CarpetCleaningServiceManagementTest {
    requires CarpetCleaningServiceManagement;

    requires io.cucumber.java;
    requires io.cucumber.core;
    requires io.cucumber.junit;

    requires junit;
    requires org.junit.jupiter.api;

    requires java.sql;

    exports features;
}
