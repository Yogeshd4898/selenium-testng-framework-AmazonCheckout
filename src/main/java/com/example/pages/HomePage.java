package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.example.base.BasePage;

/**
 * Represents the Home page of the application.
 * Provides functionality to search for products.
 */
public class HomePage extends BasePage {

    private final By searchBox = By.id("twotabsearchtextbox");

    /**
     * Constructs a HomePage instance.
     *
     * @param driver the WebDriver instance to interact with the browser
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Searches for a product by entering the product name into the search box and pressing ENTER.
     *
     * @param productName the name of the product to search for
     */
    public void searchProduct(String productName) {
        driver.findElement(searchBox).sendKeys(productName + Keys.ENTER);
    }
}
