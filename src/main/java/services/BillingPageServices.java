package services;

import support.BrowserActions;
import utils.Config;

public class BillingPageServices {
    public static boolean isBillingFormDisplayed(){
        BrowserActions.scrollToElement(Config.get("billing.details.heading.field"),
                BrowserActions.TypeOfElement.CLASSNAME);
        return BrowserActions.getElement(Config.get("billing.details.heading.field"),
                BrowserActions.TypeOfElement.CLASSNAME).isDisplayed() &&
                BrowserActions.getElement(Config.get("first.name.field"),
                        BrowserActions.TypeOfElement.ID).isDisplayed() &&
                BrowserActions.getElement(Config.get("last.name.field"),
                        BrowserActions.TypeOfElement.ID).isDisplayed() &&
                BrowserActions.getElement(Config.get("email.field"),
                        BrowserActions.TypeOfElement.ID).isDisplayed() &&
                BrowserActions.getElement(Config.get("phone.field"),
                        BrowserActions.TypeOfElement.ID).isDisplayed() &&
                BrowserActions.getElement(Config.get("country.field"),
                        BrowserActions.TypeOfElement.ID).isDisplayed() &&
                BrowserActions.getElement(Config.get("address.field"),
                        BrowserActions.TypeOfElement.ID).isDisplayed() &&
                BrowserActions.getElement(Config.get("city.field"),
                        BrowserActions.TypeOfElement.ID).isDisplayed() &&
                BrowserActions.getElement(Config.get("state.field"),
                        BrowserActions.TypeOfElement.ID).isDisplayed() &&
                BrowserActions.getElement(Config.get("postcode.field"),
                        BrowserActions.TypeOfElement.ID).isDisplayed();
    }
}
