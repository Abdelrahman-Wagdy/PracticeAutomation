package support;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

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
                    WebDriverManager.firefoxdriver().setup();
                    tlDriver.set(new org.openqa.selenium.firefox.FirefoxDriver(new FirefoxOptions()));
                }
                case "chrome" -> {
                    System.out.println("Initiating the chrome driver");
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions opt = new ChromeOptions();
                    opt.addArguments("--disable-features=InterestCohort", "--blink-settings=enable-blocked-ads=true");
                    tlDriver.set(new org.openqa.selenium.chrome.ChromeDriver(opt));
                }
                default -> {
                    throw new IllegalStateException("Invalid browser name is passed: " + browser);
                }
            }
            tlDriver.get().manage().window().maximize();
        }
    }

    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}
