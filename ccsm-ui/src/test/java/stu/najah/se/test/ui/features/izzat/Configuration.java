package stu.najah.se.test.ui.features.izzat;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;
import stu.najah.se.core.ServiceManager;
import stu.najah.se.ui.Prompter;
import stu.najah.se.ui.SceneManager;

public class Configuration extends ApplicationTestBase {

    static {
        System.setProperty("testfx.timeout", "10000");
    }

    private void setHeadlessMode() {
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
        System.setProperty("prism.order", "sw");
        System.setProperty("prism.text", "t2k");
        System.setProperty("java.awt.headless", "true");
    }

    @BeforeAll
    public static void launch() throws Exception {
        ServiceManager.initializeAdminService();
        ServiceManager.initializeEntityServices(Prompter.getInstance(), Prompter.getInstance());
        ApplicationTest.launch(SceneManager.class);
    }


    @Before
    public void selectStage() {
        targetWindow(SceneManager.getInstance().getStage());
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
