package PageObjectclass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractComponents.AbstractMethod;

public class CheckoutPage extends AbstractMethod {

	 private WebDriver driver;
	 
	 public CheckoutPage(WebDriver driver,WebDriverWait wait) {
	        super(driver,wait);
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	 
	 @FindBy(xpath = "//input[@class='input txt']")
	     WebElement cvvField;

	    @FindBy(xpath = "//div[text()='Name on Card ']/following-sibling::input")
	     WebElement nameOnCardField;

	    @FindBy(xpath = "//div[text()='Apply Coupon ']/following-sibling::input")
	     WebElement couponField;

	    @FindBy(css = ".form-group input")
	     WebElement countryInput;

	    @FindBy(css = ".ta-item")
	     List<WebElement> countrySuggestions;

	    @FindBy(css = ".btnn")
	     WebElement placeOrderBtn;
	    
	   
	    
	   
	    
	    private By countryDropdown = By.cssSelector(".ta-results");
	    
	 // --- Action Methods ---

	    public void fillCvv(String cvv) {
	        sendKeys(cvvField, cvv);
	    }

	    public void fillNameOnCard(String name) {
	        sendKeys(nameOnCardField, name);
	    }

	    public void fillCoupon(String coupon) {
	        sendKeys(couponField, coupon);
	    }

	    public void selectCountry(String country) throws InterruptedException {
	        sendKeys(countryInput, country);
	        Thread.sleep(2000);
	        waitForElementToAppear(countryDropdown);
	        selectDropdown(countrySuggestions, country);
	    }

	    public void placeOrder() throws InterruptedException {
	    	scrollAndClick(placeOrderBtn);
	      //  placeOrderBtn.click();
	    }
	    
	  
	    
}
