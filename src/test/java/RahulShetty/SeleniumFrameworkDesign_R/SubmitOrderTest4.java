package RahulShetty.SeleniumFrameworkDesign_R;

import java.io.IOException;
import java.util.HashMap;
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

public class SubmitOrderTest4 extends BaseTest {

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

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(
            System.getProperty("user.dir") + "/src/test/resources/TestData/purchaseOrder.json"
        );
        return new Object[][] {{data.get(0)}, {data.get(1)}};
    }
}