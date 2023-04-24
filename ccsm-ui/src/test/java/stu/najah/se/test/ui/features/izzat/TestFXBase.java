package stu.najah.se.test.ui.features.izzat;

import org.testfx.framework.junit5.ApplicationTest;
import stu.najah.se.ui.SceneManager;

public class TestFXBase extends ApplicationTest {

    public static void main(String[] args) throws Exception {
        ApplicationTest.launch(SceneManager.class);
        Thread.sleep(10000);
    }

}
