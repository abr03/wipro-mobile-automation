package app.wiproAssignment.com;

import app.wiproAssignment.com.constants.Constants;
import app.wiproAssignment.com.driver.Driver;
import app.wiproAssignment.com.listeners.TestListener;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;

@Listeners(TestListener.class)
public class BaseTest {
    public String platform;
    AppiumDriverLocalService service;

    @Parameters({"platform"})
    @BeforeSuite
    public void startServer(@Optional("ios")String platform){

        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withAppiumJS(new File(Constants.getAppiumPath()))
                .withIPAddress("127.0.0.1");
        if(platform.equalsIgnoreCase("android")){
            builder.usingPort(4723);
        } else if (platform.equalsIgnoreCase("ios")) {
            builder.usingPort(4725);
        }
        try{
           service= AppiumDriverLocalService.buildService(builder);
           service.start();

        } catch (AppiumServerHasNotBeenStartedLocallyException e) {
            e.printStackTrace();
        }

        System.out.println("Appium Server Started");
    }

    @Parameters({"platform","runMode"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("ios") String platform, @Optional("local") String runMode, Method method) throws Exception {
        this.platform = platform;

        Driver.initDriver(platform, runMode, method.getName());
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(){

        Driver.quitDriver();
    }

    @AfterSuite
    public void stopServer(){

        if(service!=null){
            service.stop();
        }

        System.out.println("Appium Server Stopped");
    }
}
