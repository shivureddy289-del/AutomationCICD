package RahulShetty.SeleniumFrameworkDesign_R;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import AbstractComponents.BaseTest;
import PageObjectclass.CartPage;
import PageObjectclass.CheckoutPage;
import PageObjectclass.ConfirmationPage;
import PageObjectclass.OrderPage;
import PageObjectclass.ProductCatlogPage;

public class SubmitOrderTest extends BaseTest {

    String productName = "IPHONE 13 PRO";

    @Test
    public void submitOrder() throws IOException, InterruptedException {

        // Step 1: Login and get Product Catalog page
        ProductCatlogPage productCatalog = loginpage.LoginApplication(
            prop.getProperty("email"),
            prop.getProperty("password")
        );

        // Step 2: Add product to cart
        List<WebElement> products = productCatalog.getProductLists();
        productCatalog.addProductToCart(productName);

        // Step 3: Go to cart and verify product is present
        CartPage cart = productCatalog.goToCartPage();
        boolean match = cart.verifyProductDsplay(productName);
        Assert.assertTrue(match, "FAIL: The product '" + productName + "' was NOT found in the cart.");

        // Step 4: Proceed to checkout
        cart.goToCheckout();

        // Step 5: Fill checkout details and place order
        CheckoutPage checkout = new CheckoutPage(driver, wait);
        checkout.fillCvv("453");
        checkout.fillNameOnCard("Roye");
        checkout.selectCountry("india");
        checkout.placeOrder();

        // Step 6: Verify confirmation message
        ConfirmationPage confirmationPage = new ConfirmationPage(driver, wait);
        String confirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(
            confirmationMessage.equalsIgnoreCase("Thankyou for the order."),
            "FAIL: Confirmation message did not match. Actual: " + confirmationMessage
        );
    }

    @Test(dependsOnMethods = "submitOrder")
    public void orderHistoryTest() {

        // Step 1: Login with second user
        ProductCatlogPage productCatalog = loginpage.LoginApplication(
            "rohit.gouda123@gmail.com",
            "Rohit@123"
        );

        // Step 2: Go to Order page and verify product appears in order history
        OrderPage orderPage = productCatalog.goToOrderPage();
        Assert.assertTrue(
            orderPage.verifyOrderDsplay(productName),
            "FAIL: Product '" + productName + "' was NOT found in order history."
        );
    }
}