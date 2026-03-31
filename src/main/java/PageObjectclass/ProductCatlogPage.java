package PageObjectclass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractComponents.AbstractMethod;

public class ProductCatlogPage extends AbstractMethod {

	WebDriver driver;

	public ProductCatlogPage(WebDriver driver,WebDriverWait wait) {

		super(driver,wait);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.xpath(".//button[@class='btn w-10 rounded']");
	By tostMessage = By.cssSelector("#toast-container");
	By tostMessageDisappear =By.cssSelector(".ng-animating");

	public List<WebElement> getProductLists() {
		waitForElementToAppear(productBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		waitForElementToAppear(productBy);
		for (WebElement product : products) {
			if (product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName)) {
				return product;
			}
		}
		return null;
	}

	public void addProductToCart(String productname) throws InterruptedException {
		WebElement product = getProductByName(productname); // finds internally
		WebElement btn = product.findElement(addToCart);
		scrollAndClick(btn);
		waitForElementToAppear(tostMessage);
		waitForElementDisappear(tostMessageDisappear);
		scrollToTop();
		
	}

}
