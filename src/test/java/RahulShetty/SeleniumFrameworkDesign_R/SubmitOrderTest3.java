package RahulShetty.SeleniumFrameworkDesign_R;

import java.io.IOException;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import AbstractComponents.BaseTest;
import PageObjectclass.CartPage;
import PageObjectclass.CheckoutPage;
import PageObjectclass.ConfirmationPage;
import PageObjectclass.OrderPage;
import PageObjectclass.ProductCatlogPage;

public class SubmitOrderTest3 extends BaseTest {

    @Test(dataProvider = "getData", groups = {"purchase"})
    public void submitOrder(HashMap<String, String> input)
            throws IOException, InterruptedException {

        // Step 1: Login and get product catalog
        ProductCatlogPage productCatalog = loginpage.LoginApplication(
            input.get("email"),
            input.get("password")
        );

        // Step 2: Add product to cart
        productCatalog.getProductLists();
        productCatalog.addProductToCart(input.get("product"));

        // Step 3: Go to cart and verify product
        productCatalog.scrollToTop();
        CartPage cart = productCatalog.goToCartPage();
        boolean match = cart.verifyProductDsplay(input.get("product"));
        Assert.assertTrue(match, "FAIL: '" + input.get("product") + "' was NOT found in cart.");

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

    @Test(dataProvider = "getData", dependsOnMethods = "submitOrder")
    public void orderHistoryTest(HashMap<String, String> input) {

        // Step 1: Login with same user who placed the order
        ProductCatlogPage productCatalog = loginpage.LoginApplication(
            input.get("email"),
            input.get("password")
        );

        // Step 2: Verify product appears in order history
        OrderPage orderPage = productCatalog.goToOrderPage();
        Assert.assertTrue(
            orderPage.verifyOrderDsplay(input.get("product")),
            "FAIL: Product '" + input.get("product") + "' was NOT found in order history."
        );
    }

    @DataProvider
    public Object[][] getData() {

        HashMap<String, String> map = new HashMap<>();
        map.put("email",    "mahesh0123@gmail.com");
        map.put("password", "Mahi@123");
        map.put("product",  "ADIDAS ORIGINAL");

        HashMap<String, String> map1 = new HashMap<>();
        map1.put("email",    "rohit.gouda123@gmail.com");
        map1.put("password", "Rohit@123");
        map1.put("product",  "IPHONE 13 PRO");

        return new Object[][] {{map}, {map1}};
    }
}