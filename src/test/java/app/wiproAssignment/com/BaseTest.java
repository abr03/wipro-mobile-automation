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
    AppiumDriverLocalService service2;


    @Parameters({"platform","runMode","deviceName","serverPort","systemPort"})
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

            service2= AppiumDriverLocalService.buildService(builder);
            service2.start();

        } catch (AppiumServerHasNotBeenStartedLocallyException e) {
            e.printStackTrace();
        }

        System.out.println("Appium Server Started");
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("ios") String platform,String runMode, String devicename,String serverPort,String systemPort, Method method) throws Exception {
        this.platform = platform;

        Driver.initDriver(platform, runMode, method.getName(),devicename,serverPort,systemPort);
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
