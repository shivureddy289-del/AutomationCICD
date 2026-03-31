package stepdefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import AbstractComponents.BaseTest;
import PageObjectclass.CartPage;
import PageObjectclass.CheckoutPage;
import PageObjectclass.ConfirmationPage;
import PageObjectclass.LonginPage;
import PageObjectclass.ProductCatlogPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepdefinationImplimentation  extends BaseTest{

	public LonginPage loginpage;
	ProductCatlogPage productcatl;
	CheckoutPage checkout;
	ConfirmationPage confirmMessage;
	
	
 
    // ✅ Replaces @AfterClass or @AfterMethod from TestNG
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_commerce_page() throws IOException {
		loginpage=launchApplication();
	}
	
	@Given("Logged in with username {string} and password {string}")
	public void logged_in_with_username_and_password(String username, String password) {
		 productcatl = loginpage.LoginApplication(username,password);
	   
	}
	@When("I add product {string} to Cart")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productcatl.getProductLists();
		productcatl.addProductToCart(productName);
	    
	}
	@When("Checkout {string} and Submit the order")
	public void checkout_and_submit_the_order(String productName) throws InterruptedException {
		productcatl.scrollToTop();
		CartPage cart = productcatl.goToCartPage();
		Boolean match = cart.verifyProductDsplay(productName);
		Assert.assertTrue(match);
		cart.goToCheckout();
	    checkout = new CheckoutPage(driver,wait);
		checkout.fillCvv("453");
		checkout.fillNameOnCard("Roye");
		checkout.selectCountry("india");
		checkout.placeOrder();
		
	   
	}
	@Then("{string} message is displayed on confirmation page")
	public void message_is_displayed_on_confirmation_page(String string) {
	    confirmMessage = new ConfirmationPage(driver,wait);
		String confirmessage = confirmMessage.getConfirmationMessage();
		Assert.assertTrue(confirmessage.equalsIgnoreCase("Thankyou for the order."));
	    
	}
}
