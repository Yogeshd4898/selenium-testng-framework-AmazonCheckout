package com.example.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import com.example.base.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TestNG listener for capturing test events and integrating them with ExtentReports.
 */
public class TestListener implements ITestListener {

    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    /**
     * Invoked each time before a test starts.
     *
     * @param result the test result
     */
    @Override
    public void onTestStart(ITestResult result) {
        try {
            BaseTest baseTest = (BaseTest) result.getInstance();
            // Use the getter to access the ExtentReports instance
            ExtentTest test = baseTest.getExtentReports().createTest(result.getMethod().getMethodName());
            result.setAttribute("extentTest", test);
            logger.info("Test started: {}", result.getMethod().getMethodName());
        } catch (Exception e) {
            logger.error("Error on test start: {}", result.getMethod().getMethodName(), e);
        }
    }

    /**
     * Invoked each time a test succeeds.
     *
     * @param result the test result
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = (ExtentTest) result.getAttribute("extentTest");
        if (test != null) {
            test.log(Status.PASS, "Test passed");
        }
        logger.info("Test passed: {}", result.getMethod().getMethodName());
    }

    /**
     * Invoked each time a test fails.
     *
     * @param result the test result
     */
    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = (ExtentTest) result.getAttribute("extentTest");
        if (test != null) {
            test.log(Status.FAIL, "Test failed: " + result.getThrowable());
        }
        logger.error("Test failed: {}", result.getMethod().getMethodName(), result.getThrowable());
    }

    /**
     * Invoked each time a test is skipped.
     *
     * @param result the test result
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = (ExtentTest) result.getAttribute("extentTest");
        if (test != null) {
            test.log(Status.SKIP, "Test skipped");
        }
        logger.warn("Test skipped: {}", result.getMethod().getMethodName());
    }

    /**
     * Invoked before the test context starts.
     *
     * @param context the test context
     */
    @Override
    public void onStart(ITestContext context) {
        logger.info("Test execution started: {}", context.getName());
    }

    /**
     * Invoked after the test context finishes.
     *
     * @param context the test context
     */
    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test execution finished: {}", context.getName());
    }
}
