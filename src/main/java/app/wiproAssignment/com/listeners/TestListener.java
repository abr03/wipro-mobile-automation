package app.wiproAssignment.com.listeners;

import app.wiproAssignment.com.reports.ReportManager;
import app.wiproAssignment.com.utils.ScreenshotUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;

public class TestListener implements ITestListener {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        System.out.println("=== Test Suite Execution Started ===");

        // FIX: Hard-delete the entire directory first so old runs/screenshots vanish completely
        try {
            File reportDir = new File(System.getProperty("user.dir") + "/ExtentReports");
            if (reportDir.exists()) {
                FileUtils.deleteDirectory(reportDir);
                System.out.println("Cleaned old historical ExtentReports directory.");
            }
        } catch (Exception e) {
            System.out.println("Could not clear old report directory: " + e.getMessage());
        }

        // Initialize report ONLY after the old file directory has been wiped cleanly
        extent = ReportManager.initReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Starting Test: " + result.getMethod().getMethodName());
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getMethod().getMethodName());
        test.get().log(Status.PASS, "Test passed successfully.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getMethod().getMethodName());

        test.get().log(Status.FAIL, "Test Case FAILED: " + result.getMethod().getMethodName());
        test.get().fail(result.getThrowable());

        // Capture relative screenshot path and embed it inline with the failure step
        try {
            String relativeScreenshotPath = ScreenshotUtil.takeScreenshot(result.getMethod().getMethodName());
            if (relativeScreenshotPath != null) {
                test.get().fail("Failure Screenshot:",
                        MediaEntityBuilder.createScreenCaptureFromPath(relativeScreenshotPath).build());
            } else {
                test.get().log(Status.WARNING, "Screenshot path returned null. Driver may not be active.");
            }
        } catch (Exception e) {
            test.get().log(Status.WARNING, "An error occurred while attaching screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getMethod().getMethodName());
        test.get().log(Status.SKIP, "Test skipped.");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("=== Test Suite Execution Finished ===");
        if (extent != null) {
            extent.flush();
        }
    }
}