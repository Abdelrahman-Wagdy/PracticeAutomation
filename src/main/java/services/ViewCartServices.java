package services;

import org.openqa.selenium.WebElement;
import support.BrowserActions;
import utils.Config;

public class ViewCartServices {
    private static String getItemText(String element){
        WebElement webElement = BrowserActions.getElement(element, BrowserActions.TypeOfElement.CSS_SELECTOR);
        return webElement.getText();
    }
    public static int getNumberOfItemsInCart(){
        return BrowserActions.getElements(
                Config.get("cart.items.list.field"), BrowserActions.TypeOfElement.CLASSNAME).size();
    }
    public static String getProductName(){
        return ViewCartServices.getItemText(Config.get("cart.product.name.field"));
    }
    public static String getProductPrice(){
        return ViewCartServices.getItemText(Config.get("cart.product.price.field"));
    }
    public static String getProductQuantity(){
        return BrowserActions.getElement(Config.get("cart.product.quantity.field"), BrowserActions.TypeOfElement.NAME)
                .getAttribute("value");
    }
    public static String getProductTotal(){
        return ViewCartServices.getItemText(Config.get("cart.product.subtotal.field"));
    }
    public static void proceedToCheckout(){
        BrowserActions.scrollToElement(Config.get("cart.product.name.field"), BrowserActions.TypeOfElement.CSS_SELECTOR);
        BrowserActions.click(
                Config.get("cart.proceed.to.checkout.button.field"),
                BrowserActions.TypeOfElement.CLASSNAME);
    }
}
