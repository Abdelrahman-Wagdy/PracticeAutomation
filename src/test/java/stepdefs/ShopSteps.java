package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import services.BillingPageServices;
import services.HomePageServices;
import services.ViewCartServices;
import support.BrowserActions;
import utils.Config;

public class ShopSteps {
    @Given("that the user opens the webpage successfully")
    public void that_the_user_opens_the_webpage_successfully(){
        Assert.assertEquals(BrowserActions.getURL(), Config.get("url"));
    }
    @And("that the user scrolled to the new arrivals section")
    public void that_the_user_scrolled_to_the_new_arrivals_section(){
        HomePageServices.scrollToNewArrivalSection();
    }
    @Then("the {string} book should exist")
    public void the_book_should_exist(String bookTitle){
        Assert.assertEquals(HomePageServices.getItemTitle(), bookTitle);
    }
    @And("the price should be {string}")
    public void the_price_should_be(String bookPrice){
        Assert.assertEquals(HomePageServices.getItemPrice(), bookPrice);
    }
    @When("the user adds it to basket")
    public void the_user_adds_it_to_basket() {
        HomePageServices.addItemToCart();
    }
    @And("the user opens the shopping cart")
    public void the_user_opens_the_shopping_cart(){
        HomePageServices.viewBasket();
    }
    @Then("{string} should appear in the cart priced {string}")
    public void should_appear_in_the_cart_priced(String bookTitle, String bookPrice){
        Assert.assertEquals(ViewCartServices.getNumberOfItemsInCart(), 1);
        Assert.assertEquals(ViewCartServices.getProductName(), bookTitle);
        Assert.assertEquals(ViewCartServices.getProductPrice(), bookPrice);
        Assert.assertEquals(ViewCartServices.getProductQuantity(), "1");
        Assert.assertEquals(ViewCartServices.getProductTotal(), bookPrice);
    }
    @When("the user clicks on proceed to checkout")
    public void the_user_clicks_on_proceed_to_checkout(){
        ViewCartServices.proceedToCheckout();
    }
    @Then("the billing details form is displayed")
    public void the_billing_details_form_is_displayed(){
        Assert.assertTrue(BillingPageServices.isBillingFormDisplayed());
    }
}
