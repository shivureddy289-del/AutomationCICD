package PageObjectclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractComponents.AbstractMethod;

public class LonginPage extends AbstractMethod {

	WebDriver driver;
	

	public LonginPage(WebDriver driver,WebDriverWait wait) {

		// driver initialization
		super(driver,wait); // driver and wait stored in AbstractMethod, accessible here
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}

//	WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("userEmail")));
//	emailField.sendKeys("rohit.gouda123@gmail.com");

	@FindBy(id = "userEmail")
	WebElement emailField;

//	WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("userPassword")));
//	passwordField.sendKeys("Rohit@123");

	@FindBy(id = "userPassword")
	WebElement passwordField;

	// ng-tns-c4-14 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr
	// toast-error
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

//	WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("login")));
//	loginBtn.click();

	@FindBy(id = "login")
	WebElement loginBtn;

	public ProductCatlogPage LoginApplication(String email, String password) {
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		loginBtn.click();
		ProductCatlogPage productcatl = new ProductCatlogPage(driver, wait);
		return productcatl;
	}

	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	public void goTo(String url) {
		driver.get(url);
	}
}
