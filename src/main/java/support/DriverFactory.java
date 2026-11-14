package support;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import utils.Config;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void initDriver(String browser) {
        if (tlDriver.get() == null) {
            System.out.println("DriverFactory: Initializing " + browser + " driver for thread " + Thread.currentThread().getId());
            switch (browser.toLowerCase()) {
                case "firefox" -> {
                    System.out.println("Initiating the firefox driver");
                    if (System.getProperty("webdriver.gecko.driver") == null) WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions options = new FirefoxOptions();
                    if (Boolean.parseBoolean(Config.get("headless"))) options.addArguments("-headless");
                    if (Config.get("firefox.binary") != null) options.setBinary(Config.get("firefox.binary"));
                    tlDriver.set(new org.openqa.selenium.firefox.FirefoxDriver(options));
                }
                case "chrome" -> {
                    System.out.println("Initiating the chrome driver");
                    if (System.getProperty("webdriver.chrome.driver") == null) WebDriverManager.chromedriver().setup();
                    ChromeOptions opt = new ChromeOptions();
                    if (Boolean.parseBoolean(Config.get("headless"))) opt.addArguments("--headless=new", "--window-size=1440,1000");
                    if (Config.get("chrome.binary") != null) opt.setBinary(Config.get("chrome.binary"));
                    opt.addArguments("--disable-features=InterestCohort", "--blink-settings=enable-blocked-ads=true");
                    tlDriver.set(new org.openqa.selenium.chrome.ChromeDriver(opt));
                }
                default -> {
                    throw new IllegalStateException("Invalid browser name is passed: " + browser);
                }
            }
            if (!Boolean.parseBoolean(Config.get("headless"))) tlDriver.get().manage().window().maximize();
        }
    }

    public static void quitDriver() {
        if (tlDriver.get() != null) {
            try { tlDriver.get().quit(); } finally { tlDriver.remove(); }
        }
    }
}
