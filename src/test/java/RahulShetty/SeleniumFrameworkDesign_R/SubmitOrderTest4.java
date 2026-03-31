package RahulShetty.SeleniumFrameworkDesign_R;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AbstractComponents.BaseTest;
import PageObjectclass.CartPage;
import PageObjectclass.CheckoutPage;
import PageObjectclass.ConfirmationPage;
import PageObjectclass.LonginPage;
import PageObjectclass.OrderPage;
import PageObjectclass.ProductCatlogPage;

public class SubmitOrderTest4 extends BaseTest {

//	String productname = "IPHONE 13 PRO";

	@Test(dataProvider = "getData", groups = { "purchase" })
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		// 1)LoginPageClass
		ProductCatlogPage productcatl = loginpage.LoginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productcatl.getProductLists();
		productcatl.addProductToCart(input.get("product"));

		// 3. Go to cart
		productcatl.scrollToTop();
		CartPage cart = productcatl.goToCartPage();
		Boolean match = cart.verifyProductDsplay(input.get("product"));
		Assert.assertTrue(match);
		cart.goToCheckout();

		// CheckoutPage
		CheckoutPage checkout = new CheckoutPage(driver, wait);
		checkout.fillCvv("453");
		checkout.fillNameOnCard("Roye");
		checkout.selectCountry("india");
		checkout.placeOrder();

		// ConfirmationPage
		ConfirmationPage confirmMessage = new ConfirmationPage(driver, wait);
		String confirmessage = confirmMessage.getConfirmationMessage();
		Assert.assertTrue(confirmessage.equalsIgnoreCase("Thankyou for the order."));

	}
	

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\purchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
