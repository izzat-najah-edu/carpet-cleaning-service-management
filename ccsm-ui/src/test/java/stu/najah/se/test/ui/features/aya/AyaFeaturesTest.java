package stu.najah.se.test.ui.features.aya;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports"},
        features = {"src/test/resources/features/aya"}
)
public class AyaFeaturesTest {
    /*
    This class servers as an entry point for all Cucumber tests in the 'features' directory.
    Don't place any step definitions or tests here.
     */
}
