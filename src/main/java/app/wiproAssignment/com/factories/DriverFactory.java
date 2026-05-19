package app.wiproAssignment.com.factories;

import app.wiproAssignment.com.constants.Constants;
import app.wiproAssignment.com.utils.PropertyBuilder;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.MutableCapabilities;

import java.net.URI;

public class DriverFactory {
    public static AppiumDriver getDriver(String device, String runMode, String testName) throws Exception {
        AppiumDriver driver = null;

        if (runMode.equalsIgnoreCase("remote")) {
            if (device.equalsIgnoreCase("ios")) {
                MutableCapabilities caps = new MutableCapabilities();
                caps.setCapability("platformName", PropertyBuilder.getProperty("platformNameIOS"));
                caps.setCapability("appium:app", PropertyBuilder.getProperty("appIOS"));  // The filename of the mobile app
                caps.setCapability("appium:deviceName", PropertyBuilder.getProperty("deviceNameIOS"));
                caps.setCapability("appium:automationName", PropertyBuilder.getProperty("automationNameIOS"));
                MutableCapabilities sauceOptions = new MutableCapabilities();
                sauceOptions.setCapability("username", PropertyBuilder.getProperty("username"));
                sauceOptions.setCapability("accessKey", PropertyBuilder.getProperty("accessKey"));
                sauceOptions.setCapability("build", PropertyBuilder.getProperty("appiumBuild"));
                sauceOptions.setCapability("name", testName);
                caps.setCapability("sauce:options", sauceOptions);
                caps.setCapability("appium:autoAcceptAlerts", true);
                caps.setCapability("appium:maxTypingFrequency", 10);
                URI uri = new URI("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
                driver = new IOSDriver(uri.toURL(), caps);
            } else {
                MutableCapabilities caps = new MutableCapabilities();
                caps.setCapability("platformName", PropertyBuilder.getProperty("platformNameAndroid"));
                caps.setCapability("appium:app", PropertyBuilder.getProperty("appAndroid"));  // The filename of the mobile app
                caps.setCapability("appium:deviceName", PropertyBuilder.getProperty("deviceNameAndroid"));
                caps.setCapability("appium:platformVersion", PropertyBuilder.getProperty("platformVersion"));
                caps.setCapability("appium:automationName", PropertyBuilder.getProperty("UiAutomator2"));
                MutableCapabilities sauceOptions = new MutableCapabilities();
                sauceOptions.setCapability("username", PropertyBuilder.getProperty("username"));
                sauceOptions.setCapability("accessKey", PropertyBuilder.getProperty("accessKey"));
                sauceOptions.setCapability("build", PropertyBuilder.getProperty("appiumBuild"));
                sauceOptions.setCapability("name", testName);
                sauceOptions.setCapability("appiumVersion", PropertyBuilder.getProperty("appiumVersion"));
                caps.setCapability("appium:autoGrantPermissions", true);
                caps.setCapability("sauce:options", sauceOptions);

                URI uri = new URI("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
                driver = new AndroidDriver(uri.toURL(), caps);            }
        } else {
            if (device.equalsIgnoreCase("android")) {
                UiAutomator2Options options = new UiAutomator2Options();
                options.setDeviceName(PropertyBuilder.getProperty("androidSimulatorName"));
                options.setApp(Constants.getAppFilePath());
                options.autoGrantPermissions();
                URI uri = new URI("http://127.0.0.1:" + String.valueOf(Constants.getAndroidPort()));

                driver = new AndroidDriver(uri.toURL(), options);
            } else {
                XCUITestOptions options = new XCUITestOptions();
                options.setDeviceName(PropertyBuilder.getProperty("iosSimulatorName"));
                options.setApp(Constants.getAppIosFilePath());
                options.autoAcceptAlerts();
                options.setMaxTypingFrequency(10);
                URI uri = new URI("http://127.0.0.1:" + String.valueOf(Constants.getAndroidPort()));

                driver = new IOSDriver(uri.toURL(), options);
            }
        }
        return driver;
    }
}
