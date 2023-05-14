package stu.najah.se.test.ui.features.aya;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;
import stu.najah.se.core.ServiceManager;
import stu.najah.se.ui.Prompter;
import stu.najah.se.ui.SceneManager;

public class Configuration {

    @BeforeAll
    public static void launch() throws Exception {
        ServiceManager.initializeAdminService();
        ServiceManager.initializeEntityServices(Prompter.getListener(), Prompter.getListener());
        ApplicationTest.launch(SceneManager.class);
    }

    @Before
    public void waitForBackgroundEffects() {
        WaitForAsyncUtils.waitForFxEvents();
    }

    @AfterStep
    public void waitForStepEffects() {
        WaitForAsyncUtils.waitForFxEvents();
    }

}
