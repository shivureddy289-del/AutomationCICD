package PageObjectclass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractComponents.AbstractMethod;

public class CartPage extends AbstractMethod {

	WebDriver driver;

	public CartPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// For waiting
	private By cartProductsBy = By.xpath("//div[@class='cartSection']");

	// For interacting
	@FindBy(xpath = "//div[@class=\"cartSection\"]//h3")
	List<WebElement> CartProducts;

	public void waitForCartPageToLoad() {
		wait.until(ExpectedConditions.or(ExpectedConditions.urlContains("cart"),
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cartSection']")),
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'No Products')]"))));

	}

	public Boolean verifyProductDsplay(String ProductName) {

		
		
		List<WebElement> cartSections = driver.findElements(cartProductsBy);
		if (cartSections.isEmpty()) {
			System.out.println("Cart is empty.");
			return false;
		}
		
		

		
		Boolean Match = CartProducts.stream()
				.anyMatch(CartProduct -> CartProduct.getText().equalsIgnoreCase(ProductName));
		return Match;

	}

	@FindBy(xpath = "//li[@class=\"totalRow\"]/button")
	WebElement Checkout;

	public void goToCheckout() throws InterruptedException {
		scrollAndClick(Checkout);
//		 JavascriptExecutor js = (JavascriptExecutor) driver;
//		    js.executeScript("arguments[0].scrollIntoView(true);", Checkout);
//		    js.executeScript("arguments[0].click();", Checkout); // bypasses interception
		// Checkout.click();
	}
}
