package com.example.tests;

import org.testng.annotations.Test;
import com.example.base.BaseTest;
import com.example.pages.CartPage;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.example.pages.PaymentPage;
import com.example.pages.ProductPage;
import com.fasterxml.jackson.databind.JsonNode;
import com.example.utils.DataProviderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test class for executing the end-to-end checkout flow.
 */
public class CheckoutTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutTest.class);

    /**
     * Executes the checkout flow based on provided test data.
     *
     * @param testData a JsonNode containing login, product, and payment information
     */
    @Test(dataProvider = "testData", 
    	  dataProviderClass = DataProviderUtils.class, 
          retryAnalyzer = com.example.utils.RetryAnalyzer.class)
    public void testCheckoutFlow(JsonNode testData) {
        // Extract test data from JSON
        String email = testData.path("login").path("email").asText();
        String password = testData.path("login").path("password").asText();
        String productName = testData.path("product").path("name").asText();
        String paymentOption = testData.path("payment").path("option").asText();
        
        logger.info("Starting checkout flow with email: {}, product: {}, payment option: {}",
                    email, productName, paymentOption);
        
        // Instantiate page objects
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        PaymentPage paymentPage = new PaymentPage(driver);
        
        // Execute checkout flow
        loginPage.login(email, password);
        homePage.searchProduct(productName);
        productPage.addProductToCart(productName);
        
        // Navigate to the cart page and process checkout
        cartPage.goToCart();
        cartPage.selectCartItem(productName);
        cartPage.proceedToCheckout();
        
        // Payment workflow
        paymentPage.selectPaymentOption(paymentOption);
        paymentPage.clickUseThisPaymentMethod();  // Click "Use this payment method" and wait for page refresh
        paymentPage.clickPlaceOrder();              // After refresh, click "Place Order"
        
        logger.info("Checkout flow completed successfully for product: {}", productName);
    }
}
