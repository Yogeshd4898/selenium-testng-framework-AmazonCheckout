package com.example.base;

import org.openqa.selenium.WebDriver;
import com.example.utils.WaitUtils;

/**
 * Base class for all page objects.
 * Provides common functionalities like driver and wait utilities.
 */
public class BasePage {
    protected final WebDriver driver;
    protected final WaitUtils waitUtils;

    /**
     * Constructs a BasePage instance.
     *
     * @param driver the WebDriver instance to be used for page interactions
     * @throws IllegalArgumentException if the provided driver is null
     */
    public BasePage(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver instance cannot be null.");
        }
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }
}
