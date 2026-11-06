package services;

import org.openqa.selenium.By;
import support.BrowserActions;

public class LoginPageServices {
    private static final By USERNAME = By.id("user-name");
    private static final By PASSWORD = By.id("password");
    private static final By LOGIN = By.cssSelector("[data-test='login-button']");
    private static final By ERROR = By.cssSelector("[data-test='error']");

    public boolean isDisplayed() {
        return BrowserActions.visible(USERNAME).isDisplayed()
                && BrowserActions.visible(LOGIN).isDisplayed();
    }

    public void login(String username, String password) {
        BrowserActions.fill(USERNAME, username);
        BrowserActions.fill(PASSWORD, password);
        BrowserActions.click(LOGIN);
    }

    public String errorMessage() {
        return BrowserActions.visible(ERROR).getText();
    }
}
