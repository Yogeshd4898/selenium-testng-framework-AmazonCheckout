package com.example.base;

import java.time.Duration;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BaseTest class that provides the setup and teardown for Selenium WebDriver
 * and ExtentReports.
 */
public class BaseTest {
	protected WebDriver driver;
	protected ExtentReports extent;
	private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

	/**
	 * Sets up the test environment before any test methods are run. Initializes the
	 * ExtentReports, configures the WebDriver using ChromeDriver, and applies
	 * necessary cookies to bypass CAPTCHA.
	 */
	@BeforeClass
	public void setUp() {
		// Initialize ExtentReports
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReports.html");
		extent.attachReporter(spark);

		System.setProperty("webdriver.chrome.driver","C:\\Users\\yoges\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

		// Configure Chrome options
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");

		// Initialize WebDriver instance
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		// Set implicit wait of 10 seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Navigate to the website to set the domain for cookies
		driver.get("https://www.amazon.in/");

		// Attempt to add session cookies to bypass CAPTCHA (use valid cookie values)
		try {
			driver.manage().addCookie(new Cookie("session-id", "257-2069165-6638126"));
			driver.manage().addCookie(new Cookie("session-id-time", "2082787201l"));
			driver.manage().addCookie(new Cookie("session-token", "MXKklRRLyF38j/PkXb...")); // Use full value
			driver.manage().addCookie(new Cookie("ubid-acbin", "262-7941153-5286704"));
			driver.manage().addCookie(new Cookie("i18n-prefs", "INR"));
		} catch (Exception e) {
			logger.warn("Failed to add one or more cookies: {}", e.getMessage());
		}

		// Refresh to apply the cookies and bypass CAPTCHA
		driver.navigate().refresh();
	}

	/**
	 * Tears down the test environment after all test methods have run. Flushes the
	 * ExtentReports and quits the WebDriver instance.
	 */
	@AfterClass
	public void tearDown() {
		if (extent != null) {
			extent.flush();
		}
		if (driver != null) {
			driver.quit();
		}
	}

	/**
	 * Getter for the ExtentReports instance.
	 *
	 * @return the ExtentReports instance
	 */
	public ExtentReports getExtentReports() {
		return extent;
	}
}
