package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/java/feature",
        glue = {"stepdefs"},
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

        private static final ThreadLocal<String> chromeBrowser = new ThreadLocal<>();
        private static final ThreadLocal<String> firefoxBrowser = new ThreadLocal<>();

        @BeforeClass()
        @Parameters("browser")
        public void setUpBrowser() {

                System.out.println("Setting browser to: chrome and firefox");
                chromeBrowser.set("chrome");
                firefoxBrowser.set("firefox");
        }


        // Override to ensure scenarios run in parallel
        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
                return super.scenarios();
        }
}
