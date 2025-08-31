package services;

import org.openqa.selenium.WebElement;
import support.BrowserActions;
import utils.Config;

public class HomePageServices {
    public static void scrollToNewArrivalSection(){
        BrowserActions.scrollToElement(
                Config.get("new.arrival.section"), BrowserActions.TypeOfElement.XPATH)
        ;
    }
    public static String getItemTitle(){
        WebElement itemTitle = BrowserActions.getElement(
                Config.get("think.in.html.text.field"),
                BrowserActions.TypeOfElement.XPATH
        );
        return itemTitle.getText();
    }
    public static String getItemPrice(){
        return BrowserActions.getElement(Config.get("think.in.html.price.field"),
                        BrowserActions.TypeOfElement.XPATH).getText();
    }
    public static void addItemToCart() {
        BrowserActions.click(
                Config.get("add.to.cart.button.field"),
                BrowserActions.TypeOfElement.CSS_SELECTOR
        );
    }
    public static void viewBasket(){
        BrowserActions.click(
                Config.get("view.basket.button.field"),
                BrowserActions.TypeOfElement.CSS_SELECTOR
        );
    }
}
