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
                "html:target/cucumber-reports/chrome-report.html",
                "json:target/cucumber-reports/chrome-report.json"
        },
        tags = "not @ignore"
)
public class ChromeTestRunner extends AbstractTestNGCucumberTests {

    @BeforeClass
    public void setUpClass() {
        System.out.println("Setting up Chrome TestRunner class | Thread ID: " + Thread.currentThread().getId());
    }

    @BeforeMethod
    public void setUpMethod() {
        BrowserManager.setBrowser("chrome");
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
