package AbstractComponents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjectclass.CartPage;
import PageObjectclass.OrderPage;

public class AbstractMethod {

	protected WebDriver driver;
	protected WebDriverWait wait;

	public AbstractMethod(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement CartHeder;

	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement OrderHeader;

	public CartPage goToCartPage() {
		waitForWebElementToAppear(CartHeder);
		CartHeder.click();
		 // Wait for browser to actually navigate to cart URL first
	    wait.until(ExpectedConditions.urlContains("cart"));
		CartPage cartpage = new CartPage(driver,wait);
		cartpage.waitForCartPageToLoad();
		return cartpage;

	}

	public OrderPage goToOrderPage() {
		waitForWebElementToAppear(OrderHeader);
		OrderHeader.click();
		OrderPage orderpage = new OrderPage(driver,wait);
		return orderpage;

	}

	public void waitForElementToAppear(By findBy) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}

	public void waitForWebElementToAppear(WebElement productsName) {

		wait.until(ExpectedConditions.visibilityOf(productsName));

	}

	public void waitForListOfWebElementToAppear(List<WebElement> element) {

		// wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));

	}

	public void scrollAndClick(WebElement element) throws InterruptedException {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView(true);", element);
	    wait.until(ExpectedConditions.elementToBeClickable(element)); // ← replace Thread.sleep
	    js.executeScript("arguments[0].click();", element);
	}

	public void waitForElementDisappear(By findBy) {

		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToBeClickable(WebElement element) {
	    
	    wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void scrollToTop() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);");
		Thread.sleep(500);
	}

	public void sendKeys(WebElement element, String text) {

		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(text);
	}

	public void selectDropdown(List<WebElement> options, String value) {
		for (WebElement option : options) {
			if (option.getText().equalsIgnoreCase(value)) {
				option.click();
				break;
			}
		}
	}
}
