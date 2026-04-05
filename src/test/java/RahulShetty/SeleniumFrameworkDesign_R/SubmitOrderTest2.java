package RahulShetty.SeleniumFrameworkDesign_R;

import java.io.IOException;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import AbstractComponents.BaseTest;
import PageObjectclass.CartPage;
import PageObjectclass.CheckoutPage;
import PageObjectclass.ConfirmationPage;
import PageObjectclass.OrderPage;
import PageObjectclass.ProductCatlogPage;

public class SubmitOrderTest2 extends BaseTest {

    // Store ordered details for orderHistoryTest
    private String orderedProductName;
    private String orderedEmail;
    private String orderedPassword;

    @Test(dataProvider = "getData", groups = {"purchase"})
    public void submitOrder(String email, String password, String productName)
            throws IOException, InterruptedException {

        // Store for orderHistoryTest
        this.orderedProductName = productName;
        this.orderedEmail       = email;
        this.orderedPassword    = password;

        // Step 1: Login and get product catalog
        ProductCatlogPage productCatalog = loginpage.LoginApplication(email, password);

        // Step 2: Add product to cart
        productCatalog.getProductLists();
        productCatalog.addProductToCart(productName);

        // Step 3: Go to cart and verify product
        productCatalog.scrollToTop();
        CartPage cart = productCatalog.goToCartPage();
        boolean match = cart.verifyProductDsplay(productName);
        Assert.assertTrue(match, "FAIL: '" + productName + "' was NOT found in cart.");

        // Step 4: Checkout
        cart.goToCheckout();
        CheckoutPage checkout = new CheckoutPage(driver, wait);
        checkout.fillCvv("453");
        checkout.fillNameOnCard("Roye");
        checkout.selectCountry("india");
        checkout.placeOrder();

        // Step 5: Verify confirmation message
        ConfirmationPage confirmationPage = new ConfirmationPage(driver, wait);
        String confirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(
            confirmationMessage.equalsIgnoreCase("Thankyou for the order."),
            "FAIL: Confirmation message did not match. Actual: " + confirmationMessage
        );
    }

    @Test(dependsOnMethods = "submitOrder")
    public void orderHistoryTest() {

        // Step 1: Login with same user who placed the order
        ProductCatlogPage productCatalog = loginpage.LoginApplication(
            orderedEmail,
            orderedPassword
        );

        // Step 2: Verify product appears in order history
        OrderPage orderPage = productCatalog.goToOrderPage();
        Assert.assertTrue(
            orderPage.verifyOrderDsplay(orderedProductName),
            "FAIL: Product '" + orderedProductName + "' was NOT found in order history."
        );
    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][] {
            {"mahesh0123@gmail.com",     "Mahi@123",   "ADIDAS ORIGINAL"},
            {"rohit.gouda123@gmail.com", "Rohit@123",  "IPHONE 13 PRO"}
        };
    }
}