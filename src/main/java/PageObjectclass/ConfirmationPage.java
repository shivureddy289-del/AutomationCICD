package PageObjectclass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractComponents.AbstractMethod;

public class ConfirmationPage extends AbstractMethod {

	WebDriver driver;

	public ConfirmationPage(WebDriver driver,WebDriverWait wait) {
		super(driver,wait);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	private WebElement confirmationMessage;

	private By confirmationBy = By.cssSelector(".hero-primary");

	public String getConfirmationMessage() {
		waitForElementToAppear(confirmationBy);
		return confirmationMessage.getText();
	}

}
