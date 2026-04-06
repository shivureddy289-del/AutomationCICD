package RahulShetty.SeleniumFrameworkDesign_R;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import AbstractComponents.BaseTest;
import AbstractComponents.IRetryAnalizer;
import PageObjectclass.CartPage;
import PageObjectclass.ProductCatlogPage;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = IRetryAnalizer.class)
    public void loginErrorValidation() throws IOException {

        // Attempt login with wrong credentials
        loginpage.LoginApplication("rohit.gou123@gmail.com", "Rohi@123");

        // Verify error message is displayed
        Assert.assertEquals(
            loginpage.getErrorMessage(),
            "Incorrect email or password.",
            "FAIL: Login error message did not match."
        );
    }

    @Test
    public void productErrorValidation() throws IOException, InterruptedException {

        String productName = "IPHONE 13 PRO";
        String wrongProductName = "IPHONE 16 PRO";

        // Step 1: Login and go to product catalog
        ProductCatlogPage productCatalog = loginpage.LoginApplication(
            "rohit.gouda123@gmail.com",
            "Rohit@123"
        );

        // Step 2: Add actual product to cart
        List<WebElement> products = productCatalog.getProductLists();
        productCatalog.addProductToCart(productName);

        // Step 3: Go to cart
        productCatalog.scrollToTop();
        CartPage cart = productCatalog.goToCartPage();

        // Step 4: Verify wrong product name is NOT in cart
        boolean match = cart.verifyProductDsplay(wrongProductName);
        Assert.assertFalse(
            match,
            "FAIL: Product '" + wrongProductName + "' should NOT be in cart but was found."
        );
    }
}