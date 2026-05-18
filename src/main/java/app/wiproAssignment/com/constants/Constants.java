package app.wiproAssignment.com.constants;



public class Constants {
    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/main/resources";
    public static final String PROPERTY_FILE_PATH = RESOURCES_PATH + "/config.properties";
    public static final String APP_FILE_PATH = RESOURCES_PATH + "/DemoApp-v1.0.0.apk";
    public static final String APP_IOS_FILE_PATH = RESOURCES_PATH + "/app/ios/Runner.app";
    private static final int ANDROID_PORT = 4723;
    private static final int IOS_PORT = 4725;
    private static final String IP_ADDRESS = "127.0.0.1";
    private static final String APPIUM_PATH = "appium/node_modules/appium/build/lib/main.js";
    private static final int EXPLICIT_WAIT = 30;
    private static final int MEDIUM_WAIT = 50;
    private static final int LONG_WAIT = 80;



    public static String getPropertyFilePath() {
        return PROPERTY_FILE_PATH;
    }
    public static String getAppFilePath() {
        return APP_FILE_PATH;
    }

    public static String getAppIosFilePath() {
        return APP_IOS_FILE_PATH;
    }

    public static int getAndroidPort() {
        return ANDROID_PORT;
    }
    public static int getIosPort() {
        return IOS_PORT;
    }
    public static String getIpAddress() {
        return IP_ADDRESS;
    }
    public static String getAppiumPath() {
        return APPIUM_PATH;
    }
    public static int getExplicitWait() {
        return EXPLICIT_WAIT;
    }


    public static int mediumWait() {
        return MEDIUM_WAIT;
    }
    public static int longWait() {
        return LONG_WAIT;
    }





}
