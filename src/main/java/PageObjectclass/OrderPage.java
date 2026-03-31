package PageObjectclass;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractComponents.AbstractMethod;

public class OrderPage extends AbstractMethod{
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver,WebDriverWait wait) {
		super(driver,wait);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productsName;
	
	public Boolean verifyOrderDsplay(String ProductName) {
		waitForListOfWebElementToAppear(productsName);
		Boolean Match = productsName.stream()
				.anyMatch(OrderProduct -> OrderProduct.getText().equalsIgnoreCase(ProductName));
		return Match;
	}
	
	
}
