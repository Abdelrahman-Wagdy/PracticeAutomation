package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import support.BrowserActions;
import support.BrowserManager;
import support.DriverFactory;

public class Hooks {
    @Before
    public void setUp() {

        String browser = BrowserManager.getBrowser();

        System.out.println("Starting test with browser: " + browser + " | Thread ID: " + Thread.currentThread().getId());
        DriverFactory.initDriver(browser);
        BrowserActions.navigateToURL();
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
        BrowserManager.clear();
    }
}
