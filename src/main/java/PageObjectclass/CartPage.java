package PageObjectclass;

import java.util.List;
import org.openqa.selenium.By;
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

    // Locators
    private By cartItemTitlesBy = By.xpath("//div[@class='cartSection']//h3");
    private By emptyCartMsgBy   = By.xpath("//*[contains(text(),'No Products')]");

    @FindBy(xpath = "//li[@class='totalRow']/button")
    WebElement Checkout;

    // Wait for cart page to fully load
    public void waitForCartPageToLoad() {
        wait.until(ExpectedConditions.urlContains("cart"));
        wait.until(ExpectedConditions.or(
            ExpectedConditions.visibilityOfElementLocated(cartItemTitlesBy),
            ExpectedConditions.visibilityOfElementLocated(emptyCartMsgBy)
        ));
    }

    // Verify if a specific product is present in the cart
    public Boolean verifyProductDsplay(String productName) {
        waitForCartPageToLoad();

        List<WebElement> cartItems = driver.findElements(cartItemTitlesBy);

        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty - no products found.");
            return false;
        }

        // Print all cart items for debugging
        System.out.println("Products found in cart:");
        for (WebElement item : cartItems) {
            System.out.println("  -> " + item.getText());
        }

        // Check if the expected product exists in cart
        boolean match = cartItems.stream()
            .anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(productName));

        if (match) {
            System.out.println("PASS: '" + productName + "' found in cart.");
        } else {
            System.out.println("FAIL: '" + productName + "' NOT found in cart.");
        }

        return match;
    }

    // Navigate to checkout
    public void goToCheckout() throws InterruptedException {
        scrollAndClick(Checkout);
    }
}