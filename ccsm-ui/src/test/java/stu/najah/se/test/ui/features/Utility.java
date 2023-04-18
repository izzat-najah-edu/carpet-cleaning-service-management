package stu.najah.se.test.ui.features;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import javafx.application.Platform;
import org.testfx.api.FxRobot;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.util.WaitForAsyncUtils;
import stu.najah.se.ui.SceneManager;

import static org.junit.Assert.assertTrue;

public class Utility {

    @BeforeAll
    public static void launch() {
        // start the application
        SceneManager.main(new String[]{});
    }

    @AfterAll
    public static void exit() {
        // close the application
        Platform.exit();
    }

    @BeforeStep
    public static void hold() {
        // wait for gui effects
        WaitForAsyncUtils.waitForFxEvents();
    }

    /**
     * Asserts the node is visible on screen;
     * @param query of the node
     */
    public static void assertVisible(String query, FxRobot robot) {
        assertTrue(NodeMatchers.isVisible().matches(robot.lookup(query).query()));
    }

}
