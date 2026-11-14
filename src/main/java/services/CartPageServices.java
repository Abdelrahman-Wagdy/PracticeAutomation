package services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import support.BrowserActions;
import support.DriverFactory;

public class CartPageServices {
    private static final By LIST = By.cssSelector("[data-test='cart-list']");
    private static final By ITEM = By.cssSelector("[data-test='inventory-item']");

    public int itemCount() {
        BrowserActions.visible(LIST);
        return DriverFactory.getDriver().findElements(ITEM).size();
    }

    private WebElement firstItem() {
        BrowserActions.visible(LIST);
        return BrowserActions.visible(ITEM);
    }

    public String productName() {
        return firstItem().findElement(By.cssSelector("[data-test='inventory-item-name']")).getText();
    }

    public String productPrice() {
        return firstItem().findElement(By.cssSelector("[data-test='inventory-item-price']")).getText();
    }

    public String quantity() {
        return firstItem().findElement(By.cssSelector("[data-test='item-quantity']")).getText();
    }
}
