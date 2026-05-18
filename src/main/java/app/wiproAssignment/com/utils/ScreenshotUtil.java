package app.wiproAssignment.com.utils;

import app.wiproAssignment.com.driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;

public class ScreenshotUtil {

    public static String takeScreenshot(String testName) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timeStamp + ".png";

        try {
            if (DriverManager.getDriver() == null) {
                System.out.println("Screenshot skip: DriverManager.getDriver() is null.");
                return null;
            }

            // Capture screenshot from the current thread-safe driver instance
            File source = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);

            // Force generation of target directories if missing
            String targetDir = System.getProperty("user.dir") + "/ExtentReports/screenshots/";
            System.out.println("debug"+targetDir);
            File directory = new File(targetDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File finalDestination = new File(targetDir + fileName);
            FileUtils.copyFile(source, finalDestination);

            // Return relative path so Extent HTML can load it locally
            return "screenshots/" + fileName;

        } catch (Exception e) {
            System.out.println("Failed to capture screenshot due to exception: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}