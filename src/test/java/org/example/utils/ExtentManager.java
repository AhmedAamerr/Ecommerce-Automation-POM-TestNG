package org.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    // Initialize the report
    public static void initReport() {
        if (extent == null) {  // Prevent reinitialization
            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Host Name", "Localhost");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("User Name", "Ahmed Aamer");
        }
    }

    // Start a test case
    public static void startTest(String testName) {
        if (extent == null) {
            initReport();
        }
        test = extent.createTest(testName);
    }

    // Log information
    public static ExtentTest getTest() {
        if (test == null) {
            throw new IllegalStateException("ExtentTest instance not initialized. Please start the test with ExtentManager.");
        }
        return test;
    }

    // Flush the report
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
