package com.example.utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for capturing and saving screenshots during test execution.
 */
public class ScreenshotUtils {

    private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtils.class);

    /**
     * Captures a screenshot using the provided WebDriver and saves it to the specified location.
     *
     * @param driver         the WebDriver instance used to capture the screenshot
     * @param screenshotName the desired name for the screenshot file (without extension)
     * @return the absolute path of the saved screenshot file
     */
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        final TakesScreenshot ts = (TakesScreenshot) driver;
        final File source = ts.getScreenshotAs(OutputType.FILE);
        final String destination = System.getProperty("user.dir") + "/target/screenshots/" + screenshotName + ".png";
        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (IOException e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage(), e);
        }
        return destination;
    }
}
