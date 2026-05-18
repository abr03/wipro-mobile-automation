package app.wiproAssignment.com.driver;

import app.wiproAssignment.com.factories.DriverFactory;

import java.net.MalformedURLException;

public class Driver {

    public Driver() {

    }

    public static void initDriver(String device, String runMode, String testName) throws Exception {

        if (DriverManager.getDriver()==null) {
            try {
                DriverManager.setDriver(DriverFactory.getDriver(device, runMode, testName));
                System.out.println("AppiumDriver initialized for device: " + device);
            } catch (MalformedURLException e) {
                System.err.println("Error initializing AppiumDriver: " + e.getMessage());
                throw new Exception("Please check the capabilities of the device", e);
            }
        }
    }

    public static void quitDriver() {
        if (DriverManager.getDriver()!=null) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}
