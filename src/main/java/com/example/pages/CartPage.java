package com.example.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.example.base.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the Cart page and provides methods to interact with cart items.
 */
public class CartPage extends BasePage {
    
    private static final Logger logger = LoggerFactory.getLogger(CartPage.class);

    private final By cartItem = By.cssSelector("div.sc-list-item[data-asin]");
    private final By productTitle = By.cssSelector("h4.a-text-normal span.a-truncate-full");
    private final By checkbox = By.cssSelector("div.a-checkbox input[type='checkbox']");
    private final By proceedButton = By.name("proceedToRetailCheckout");

    /**
     * Constructor for CartPage.
     *
     * @param driver the WebDriver instance to interact with the browser
     */
    public CartPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Scrolls to the cart icon and clicks it to navigate to the cart page.
     */
    public void goToCart() {
        WebElement cartLink = driver.findElement(By.id("nav-cart-text-container"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartLink);
        cartLink.click();
    }
    
    /**
     * Selects a cart item by matching its product name and clicking its corresponding checkbox.
     *
     * @param desiredProductName the name of the product to select in the cart
     */
    public void selectCartItem(String desiredProductName) {
        List<WebElement> items = driver.findElements(cartItem);
        for (WebElement item : items) {
            try {
                WebElement titleElement = item.findElement(productTitle);
                String cartProduct = titleElement.getText().trim();
                logger.info("Found Cart Product: {}", cartProduct);
                if (cartProduct.equalsIgnoreCase(desiredProductName)) {
                    WebElement cb = item.findElement(checkbox);
                    if (!cb.isSelected()){
                        cb.click();
                        logger.info("Checkbox clicked for: {}", cartProduct);
                    }
                    break;
                }
            } catch(Exception e) {
                logger.warn("Cart item skipped due to exception: {}", e.getMessage());
            }
        }
    }
    
    /**
     * Clicks the proceed button to navigate to the checkout page.
     */
    public void proceedToCheckout() {
        driver.findElement(proceedButton).click();
    }
}
