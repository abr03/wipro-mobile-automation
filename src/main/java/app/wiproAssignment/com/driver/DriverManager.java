package app.wiproAssignment.com.driver;

import io.appium.java_client.AppiumDriver;


public class DriverManager {



    private static final ThreadLocal<AppiumDriver> driver= new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    static void setDriver(AppiumDriver ad){
        if(ad!=null){
            driver.set(ad);
        }
    }

    static void unload(){

        driver.remove();

    }


}
