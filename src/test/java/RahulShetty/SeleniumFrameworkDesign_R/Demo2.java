package RahulShetty.SeleniumFrameworkDesign_R;

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

import PageObjectclass.LonginPage;

public class Demo2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String productname = "IPHONE 13 PRO";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");

		WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("userEmail")));
		emailField.sendKeys("rohit.gouda123@gmail.com");

		WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("userPassword")));
		passwordField.sendKeys("Rohit@123");

		WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("login")));
		loginBtn.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Wait for products to load
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
/*
		// Find and add product to cart
		for (WebElement product : products) {
			String productName = product.findElement(By.tagName("b")).getText();

			if (productName.equalsIgnoreCase(productname)) {

				// (.// means search from CURRENT element onwards)
				WebElement addToCartBtn = product.findElement(By.xpath(".//button[@class='btn w-10 rounded']"));

				// Scroll into view then JS click
				js.executeScript("arguments[0].scrollIntoView(true);", addToCartBtn);

				Thread.sleep(500);

				js.executeScript("arguments[0].click();", addToCartBtn);

				System.out.println("Added to cart: " + productName);
				break;

			}
		}

		// After clicking add to cart page will load
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

		// Scroll back to top so cart button in navbar is visible
		js.executeScript("window.scrollTo(0, 0);");
		Thread.sleep(500);

		WebElement CartButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@routerlink='/dashboard/cart']")));
		CartButton.click();
		System.out.println("Navigated to cart");

		// here we fetch all cartproducts using list and using java stream we matching
		// that product equals to product name is yes it will return tru or else it
		// returns false

		List<WebElement> CartProducts = wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class=\"cartSection\"]//h3")));

		Boolean Match = CartProducts.stream()
				.anyMatch(CartProduct -> CartProduct.getText().equalsIgnoreCase(productname));

		// More meaningful - describes what went wrong on failure
		Assert.assertTrue(Match, "Product not found in cart");

		WebElement Checkout = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class=\"totalRow\"]/button")));
		Checkout.click();

		WebElement Cvv = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class=\"input txt\"]")));
		Cvv.sendKeys("583");

		WebElement NameOneCard = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[text()=\"Name on Card \"]/following-sibling::input")));
		NameOneCard.sendKeys("rohit");

		WebElement ApplyCopen = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[text()=\"Apply Coupon \"]/following-sibling::input")));
		ApplyCopen.sendKeys("rahulshetty123");

//		WebElement ApplyCopenBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn")));
//		ApplyCopenBtn.click();

//		WebElement SearchCountryField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".form-group input")));
//		SearchCountryField.sendKeys("Australia");

		// 1. Find the input field and type
		WebElement countryInput = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".form-group input")));
		countryInput.sendKeys("Austria");

		Thread.sleep(2000);
		// 2. Wait for suggestions to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		// 3. Fetch all suggestions
		List<WebElement> suggestions = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-item")));

		// 4. Select matching option using for each
		for (WebElement suggestion : suggestions) {
			if (suggestion.getText().equalsIgnoreCase("Austria")) {
				suggestion.click();
				break;
			}
		}

		WebElement PlaceOrder = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btnn")));
		PlaceOrder.click();

		// Wait for confirmation page to load
		String ConfirmMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary"))).getText();

		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("Thankyou for the order."));
*/
	}
}
