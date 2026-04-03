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
import AbstractComponents.IRetryAnalizer;
import PageObjectclass.CartPage;
import PageObjectclass.CheckoutPage;
import PageObjectclass.ConfirmationPage;
import PageObjectclass.LonginPage;
import PageObjectclass.ProductCatlogPage;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"ErrroHandling"},retryAnalyzer=IRetryAnalizer.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		String productname = "IPHONE 13 PRO";

		// 1)LoginPageClass
		loginpage.LoginApplication("rohit.gou123@gmail.com", "Rohi@123");
		Assert.assertEquals("Incorrect email or password.", loginpage.getErrorMessage());
	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productname = "IPHONE 13 PRO";

		// 1)LoginPageClass
		ProductCatlogPage productcatl = loginpage.LoginApplication("rohit.gouda123@gmail.com", "Rohit@123");
		List<WebElement> products = productcatl.getProductLists();
		productcatl.addProductToCart(productname);

		// 3. Go to cart
		productcatl.scrollToTop();
		CartPage cart=productcatl.goToCartPage(); // just clicks, returns void
		Boolean match = cart.verifyProductDsplay("IPHONE 16 PRO");
		Assert.assertFalse(match);

	}

}
