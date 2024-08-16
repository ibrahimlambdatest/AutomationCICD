package test.StepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.TestComponents.BaseTest;
import test.pageobjects.CheckoutPage;
import test.pageobjects.LandingPage;
import test.pageobjects.OrderConfirmationPage;
import test.pageobjects.PlaceOrderPage;
import test.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue pLogue;
	public CheckoutPage chkoutPage;
	public PlaceOrderPage poPage;
	public OrderConfirmationPage orderConfirmationPage;
	
	
	@Given("I land on the ecommerce page")
	public void I_landed_on_the_ecommerce_page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		pLogue = landing.loginApplication(username, password);
	}
	
    @When("^I add product (.+) to cart$")
    public void add_product_to_cart(String productName) throws InterruptedException, IOException {
		chkoutPage = pLogue.clickAddToCart(productName);
    }
    
    @And("^checkout and submit order$")
    public void checkout_and_submit_order() throws InterruptedException{
		PlaceOrderPage poPage = chkoutPage.clickCheckout();
		orderConfirmationPage = poPage.placeOrder();
    }
    
    @Then("{string} is displayed on the confirmation page")
    public void message_displayed_on_confirmation_page(String string) {
    	Assert.assertTrue(orderConfirmationPage.confirmThankyouMessage().equalsIgnoreCase(string));
    }
    
    @Then("{string} is displayed on the login page")
    public void error_is_displayed_on_the_login_page(String message) {
    	Assert.assertEquals(landing.returnErrorMessage(),"Incorrect email or password.");
    }
 
}
