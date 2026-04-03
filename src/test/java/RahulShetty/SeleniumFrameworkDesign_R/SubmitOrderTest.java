package RahulShetty.SeleniumFrameworkDesign_R;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import AbstractComponents.BaseTest;
import PageObjectclass.CartPage;
import PageObjectclass.CheckoutPage;
import PageObjectclass.ConfirmationPage;
import PageObjectclass.LonginPage;
import PageObjectclass.OrderPage;
import PageObjectclass.ProductCatlogPage;

public class SubmitOrderTest extends BaseTest {

	String productname = "IPHONE 13 PRO";

	@Test
	public void SubmitOrder() throws IOException, InterruptedException {

		// 1)LoginPageClass
		ProductCatlogPage productcatl = loginpage.LoginApplication(prop.getProperty("email"),
				prop.getProperty("password"));
		List<WebElement> products = productcatl.getProductLists();
		productcatl.addProductToCart(productname);

		// 3. Go to cart
		productcatl.scrollToTop();
		CartPage cart = productcatl.goToCartPage();
		Boolean match = cart.verifyProductDsplay(productname);
		Assert.assertTrue(match, "FAIL: The product '" + productname + "' was NOT found in the cart list.");
		//Assert.assertTrue(match);
		cart.goToCheckout();

		// CheckoutPage
		CheckoutPage checkout = new CheckoutPage(driver,wait);
		checkout.fillCvv("453");
		checkout.fillNameOnCard("Roye");
		checkout.selectCountry("india");
		checkout.placeOrder();

		// ConfirmationPage
		ConfirmationPage confirmMessage = new ConfirmationPage(driver,wait);
		String confirmessage = confirmMessage.getConfirmationMessage();
		Assert.assertTrue(confirmessage.equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dependsOnMethods = "SubmitOrder")
	public void OrderHistoryTest() {
		// "IPHONE 13 PRO disply in orderhistory or not
		ProductCatlogPage OrderPage = loginpage.LoginApplication("rohit.gouda123@gmail.com", "Rohit@123");
		OrderPage orderpage = OrderPage.goToOrderPage();
		Assert.assertTrue(orderpage.verifyOrderDsplay(productname));

	}

}
