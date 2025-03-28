package com.example.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Utility class for handling explicit waits in Selenium WebDriver.
 */
public class WaitUtils {

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * Constructs a WaitUtils instance with a default wait time of 10 seconds.
     *
     * @param driver the WebDriver instance to use for waiting
     * @throws IllegalArgumentException if the provided driver is null
     */
    public WaitUtils(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver instance cannot be null.");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Default wait time
    }

    /**
     * Waits until the specified WebElement is clickable.
     *
     * @param element the WebElement to wait for
     */
    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits until the specified WebElement is visible on the page.
     *
     * @param element the WebElement to wait for
     */
    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until an element is present in the DOM.
     *
     * @param locator the locator used to find the element
     */
    public void waitForElementToBePresent(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
