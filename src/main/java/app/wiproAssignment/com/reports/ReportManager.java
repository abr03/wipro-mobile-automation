package app.wiproAssignment.com.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class ReportManager {
    private static ExtentReports extent;

    public static synchronized ExtentReports initReport() {
        if (extent == null) {
            String reportDirPath = System.getProperty("user.dir") + "/ExtentReports";
            File reportDir = new File(reportDirPath);

            // Wipe out historical data completely before building the new reporter instance
            try {
                if (reportDir.exists()) {
                    FileUtils.deleteDirectory(reportDir);
                    System.out.println(">>> Cleared old ExtentReports directory.");
                }
            } catch (Exception e) {
                System.out.println(">>> Note: Could not clear old report directory: " + e.getMessage());
            }

            ExtentSparkReporter spark = new ExtentSparkReporter(reportDirPath + "/index.html");
            spark.config().setReportName("Wipro Automation Assignment Results");
            spark.config().setDocumentTitle("Test Execution Report");
            spark.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Environment", "Staging");

        }
        return extent;
    }
}