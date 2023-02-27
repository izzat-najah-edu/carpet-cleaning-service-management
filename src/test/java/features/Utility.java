package features;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import org.testfx.api.FxRobot;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.util.WaitForAsyncUtils;
import stu.najah.se.Navigator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Utility {

    @BeforeAll
    public static void launch() {
        // start the application
        Navigator.main(new String[]{});
    }

    @AfterAll
    public static void exit() {
        // close the application
        Navigator.exit();
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
