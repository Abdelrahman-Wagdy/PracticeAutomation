package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/** Optional single-browser runner; BrowserManager defaults to Chrome. */
@CucumberOptions(
        features = "classpath:features",
        glue = {"stepdefs"},
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        tags = "not @ignore"
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
