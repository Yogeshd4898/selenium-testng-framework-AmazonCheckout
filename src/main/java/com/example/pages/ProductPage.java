package com.example.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.example.base.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the Product page and provides functionalities to interact with products,
 * such as adding a product to the cart.
 */
public class ProductPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ProductPage.class);

    // Locator for product titles
    private final By productNames = By.cssSelector(".a-size-medium.a-spacing-none.a-color-base.a-text-normal");

    /**
     * Constructs a ProductPage instance.
     *
     * @param driver the WebDriver instance to interact with the browser
     */
    public ProductPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Adds a product to the cart by matching the product name.
     *
     * @param desiredProductName the exact name of the product to add to the cart
     */
    public void addProductToCart(String desiredProductName) {
        logger.info("Attempting to add product '{}' to the cart.", desiredProductName);
        List<WebElement> products = driver.findElements(productNames);
        boolean productFound = false;
        
        for (WebElement product : products) {
            String productName = product.getText().trim();
            if (productName.equalsIgnoreCase(desiredProductName)) {
                // Find the parent container and then the add-to-cart button
                WebElement productContainer = product.findElement(By.xpath("ancestor::div[@data-asin]"));
                WebElement addToCartButton = productContainer.findElement(By.cssSelector("button[name='submit.addToCart']"));
                addToCartButton.click();
                logger.info("Product '{}' added to cart.", productName);
                productFound = true;
                break;
            }
        }
        
        if (!productFound) {
            logger.warn("Product '{}' not found on the product page.", desiredProductName);
        }
    }
}
