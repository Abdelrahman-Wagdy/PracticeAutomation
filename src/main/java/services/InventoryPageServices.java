package services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import support.BrowserActions;

public class InventoryPageServices {
    private static final By LIST = By.cssSelector("[data-test='inventory-list']");
    private static final By ITEM = By.cssSelector("[data-test='inventory-item']");
    private static final By NAME = By.cssSelector("[data-test='inventory-item-name']");
    private static final By PRICE = By.cssSelector("[data-test='inventory-item-price']");

    public boolean isDisplayed() {
        return BrowserActions.visible(LIST).isDisplayed();
    }

    private WebElement product(String name) {
        BrowserActions.visible(LIST);
        return BrowserActions.waitFor().until(driver -> driver.findElements(ITEM).stream()
                .filter(item -> item.findElement(NAME).getText().equals(name))
                .findFirst().orElse(null));
    }

    public String priceOf(String name) {
        return product(name).findElement(PRICE).getText();
    }

    public void addToCart(String name) {
        WebElement button = product(name).findElement(By.cssSelector("button[data-test^='add-to-cart-']"));
        BrowserActions.waitFor().until(ExpectedConditions.elementToBeClickable(button)).click();
        BrowserActions.waitFor().until(ExpectedConditions.textToBePresentInElementLocated(
                By.cssSelector("[data-test='shopping-cart-badge']"), "1"));
    }

    public void openCart() {
        BrowserActions.click(By.cssSelector("[data-test='shopping-cart-link']"));
    }
}
