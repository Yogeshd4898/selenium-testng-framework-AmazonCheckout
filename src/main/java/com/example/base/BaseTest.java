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
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {
	protected WebDriver driver;
	protected ExtentReports extent;
	private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

	@BeforeClass
	public void setUp() {
		// ✅ Configure ExtentSparkReporter with proper folder structure
		ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReports/ExtentReport.html");
		spark.config().setReportName("Amazon Checkout Automation Report");
		spark.config().setDocumentTitle("Amazon Automation Results");
		spark.config().setTheme(Theme.STANDARD); // Optional: Theme.DARK

		// ✅ Create ExtentReports instance
		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Tester", "Yogesh Desai");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Browser", "Chrome");

		// ✅ Setup ChromeDriver
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\yoges\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// ✅ Load initial page to set cookies for captcha bypass
		driver.get("https://www.amazon.in/");

		try {
			driver.manage().addCookie(new Cookie("session-id", "257-2069165-6638126"));
			driver.manage().addCookie(new Cookie("session-id-time", "2082787201l"));
			driver.manage().addCookie(new Cookie("session-token", "MXKklRRLyF38j/PkXb...")); // Full value recommended
			driver.manage().addCookie(new Cookie("ubid-acbin", "262-7941153-5286704"));
			driver.manage().addCookie(new Cookie("i18n-prefs", "INR"));
		} catch (Exception e) {
			logger.warn("Failed to add one or more cookies: {}", e.getMessage());
		}

		driver.navigate().refresh();
	}

	@AfterClass
	public void tearDown() {
		if (extent != null) {
			extent.flush(); // ✅ Write the HTML report
		}
		if (driver != null) {
			driver.quit();
		}
	}

	public ExtentReports getExtentReports() {
		return extent;
	}
}
