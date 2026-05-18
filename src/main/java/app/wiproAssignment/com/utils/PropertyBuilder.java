package app.wiproAssignment.com.utils;

import app.wiproAssignment.com.constants.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyBuilder {

    private static Properties property = new Properties();

    static {
        try {
            FileInputStream file = new FileInputStream(Constants.getPropertyFilePath());

            property.load(file);

        } catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties");
        }
    }

    public static String getProperty(String key){
        return property.getProperty(key);
    }
}