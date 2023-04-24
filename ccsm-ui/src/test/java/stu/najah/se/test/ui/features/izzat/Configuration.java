package stu.najah.se.test.ui.features.izzat;

import io.cucumber.java.BeforeAll;
import stu.najah.se.core.ServiceManager;
import stu.najah.se.ui.Prompter;

public class Configuration {

    @BeforeAll
    public void initialize() {
        ServiceManager.initializeAdminService();
        ServiceManager.initializeEntityServices(Prompter.getInstance());
    }

}
