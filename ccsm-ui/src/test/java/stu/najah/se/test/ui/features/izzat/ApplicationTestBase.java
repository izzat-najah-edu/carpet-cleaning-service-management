package stu.najah.se.test.ui.features.izzat;

import javafx.scene.Node;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ApplicationTestBase extends ApplicationTest {

    public void confirmAlert() {
        Node alert = lookup(".alert").query();
        assertNotNull(alert);
        assertTrue(alert.isVisible());
        clickOn("OK");
    }

}
