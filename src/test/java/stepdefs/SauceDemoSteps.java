package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import services.LoginPageServices;
import services.InventoryPageServices;
import services.CartPageServices;
import utils.Config;

public class SauceDemoSteps {
    private final LoginPageServices loginPage = new LoginPageServices();
    private final InventoryPageServices inventoryPage = new InventoryPageServices();
    private final CartPageServices cartPage = new CartPageServices();
    private String selectedPrice;

    @Given("the SauceDemo login page is displayed")
    public void loginPageIsDisplayed() {
        Assert.assertTrue(loginPage.isDisplayed(), "Login page should be displayed");
    }

    @When("I log in with the configured valid credentials")
    public void loginWithValidCredentials() {
        loginPage.login(Config.get("username"), Config.get("password"));
    }

    @When("I log in with username {string} and password {string}")
    public void loginWithCredentials(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("the inventory page is displayed")
    public void inventoryIsDisplayed() {
        Assert.assertTrue(inventoryPage.isDisplayed(), "Inventory should be displayed");
    }

    @Then("the login error is {string}")
    public void loginErrorIs(String message) {
        Assert.assertEquals(loginPage.errorMessage(), message);
        Assert.assertTrue(loginPage.isDisplayed(), "Failed login should remain on the login page");
    }

    @When("I add {string} to the cart")
    public void addProduct(String name) {
        selectedPrice = inventoryPage.priceOf(name);
        inventoryPage.addToCart(name);
    }

    @When("I open the cart")
    public void openCart() {
        inventoryPage.openCart();
    }

    @Then("the cart contains one {string} at its inventory price")
    public void verifyCart(String name) {
        Assert.assertEquals(cartPage.itemCount(), 1, "Cart should contain exactly one product");
        Assert.assertEquals(cartPage.productName(), name);
        Assert.assertEquals(cartPage.productPrice(), selectedPrice);
        Assert.assertEquals(cartPage.quantity(), "1");
    }
}
