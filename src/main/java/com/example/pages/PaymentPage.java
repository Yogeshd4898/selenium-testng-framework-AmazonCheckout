package com.example.pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.example.base.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the Payment page and provides methods to interact with payment options.
 */
public class PaymentPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(PaymentPage.class);

    // Locator for payment option container (adjust as needed)
    private final By paymentOptions = By.xpath("//form//div/div/div/div[2]/div[2]/div/div/div/div/div[2]/div/div");

    // Locator for the "Use this payment method" button
    private final By usePaymentMethodButton = By.xpath("/html/body/div[5]/div[1]/div/div/div/div[2]/div/div[8]/div[2]/div[2]/div/span/span/span/input");

    // Locator for the "Place your order" button
    private final By placeOrderButton = By.xpath("/html/body/div[5]/div[1]/div/div/div/div[2]/div/div[13]/div[3]/div/div[1]/form/span/span[1]/span/span/input");

    /**
     * Constructs a PaymentPage instance.
     *
     * @param driver the WebDriver instance to interact with the browser
     */
    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Selects a payment option by matching the desired payment option text.
     *
     * @param desiredPaymentOption the payment option to select (e.g., "Credit Card")
     */
    public void selectPaymentOption(String desiredPaymentOption) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> options = driver.findElements(paymentOptions);

        for (WebElement option : options) {
            try {
                // Locate the label containing the payment method text within the option container.
                WebElement label = option.findElement(By.tagName("label"));
                String optionText = label.getText().trim();

                if (optionText.contains(desiredPaymentOption)) {
                    // Locate the associated radio button within the same container.
                    WebElement radioButton = option.findElement(By.xpath(".//input[@type='radio']"));

                    // Click the option if its radio button is not already selected.
                    if (!radioButton.isSelected()) {
                        wait.until(ExpectedConditions.elementToBeClickable(label)).click();
                        logger.info("Selected Payment Option: {}", desiredPaymentOption);
                    }
                    break;
                }
            } catch (Exception e) {
                logger.warn("Skipping an option due to missing elements: {}", e.getMessage());
            }
        }
    }

    /**
     * Clicks on the "Use this payment method" button, waits for the page to refresh,
     * and ensures the "Place your order" button is loaded.
     */
    public void clickUseThisPaymentMethod() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait for the "Use this payment method" button to be clickable
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(usePaymentMethodButton));

        // Scroll the button into view to ensure it is visible
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

        // Optionally wait for the button to be visible
        wait.until(ExpectedConditions.visibilityOf(button));

        // Click the button
        button.click();
        logger.info("Clicked on 'Use this payment method' button.");

        // Wait for the page to refresh: wait until the old button becomes stale...
        wait.until(ExpectedConditions.stalenessOf(button));
        // ...then wait for the "Place your order" button to be present in the refreshed DOM.
        wait.until(ExpectedConditions.presenceOfElementLocated(placeOrderButton));
    }

    /**
     * Clicks on the "Place your order" button.
     */
    public void clickPlaceOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Locate and click the "Place your order" button
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
        logger.info("Clicked on 'Place your order' button.");
    }
}
