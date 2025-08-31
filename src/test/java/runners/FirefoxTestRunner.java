package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import support.BrowserManager;

@CucumberOptions(
        features = "src/test/java/feature",
        glue = {"stepdefs"},
        plugin = {
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "html:target/cucumber-reports/firefox-report.html",
                "json:target/cucumber-reports/firefox-report.json"
        },
        tags = "not @ignore"
)
public class FirefoxTestRunner extends AbstractTestNGCucumberTests {

    @BeforeClass
    public void setUpClass() {
        System.out.println("Setting up Firefox TestRunner class | Thread ID: " + Thread.currentThread().getId());
    }

    @BeforeMethod
    public void setUpMethod() {
        BrowserManager.setBrowser("firefox");
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
