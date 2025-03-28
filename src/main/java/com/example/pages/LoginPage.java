package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.example.base.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the Login page and provides methods to perform user authentication.
 */
public class LoginPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    private final By signInLink = By.id("nav-link-accountList");
    private final By emailField = By.name("email");
    private final By continueButton = By.className("a-button-input");
    private final By passwordField = By.id("ap_password");
    private final By submitButton = By.id("signInSubmit");

    /**
     * Constructs a LoginPage instance.
     *
     * @param driver the WebDriver instance to interact with the browser
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Performs the login action using the provided email and password.
     *
     * @param email    the user's email address
     * @param password the user's password
     */
    public void login(String email, String password) {
        logger.info("Clicking on the sign-in link.");
        driver.findElement(signInLink).click();
        
        logger.info("Entering email: {}", email);
        driver.findElement(emailField).sendKeys(email);
        
        logger.info("Clicking on the continue button.");
        driver.findElement(continueButton).click();
        
        logger.info("Entering password.");
        driver.findElement(passwordField).sendKeys(password);
        
        logger.info("Clicking on the submit button to log in.");
        driver.findElement(submitButton).click();
    }
}
