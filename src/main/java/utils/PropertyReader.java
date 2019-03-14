package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static PropertyReader instance;
    private static Properties property;
    private static String propertyFilePath;

    private PropertyReader() {
    }

    public static synchronized PropertyReader getInstance() {
        if (instance == null) {
            propertyFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\setting.properties";
            instance = new PropertyReader();
            instance.load();
        }
        return instance;
    }

    private void load() {
        property = new Properties();
        try {
            property.load(new FileInputStream(propertyFilePath));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Can't find file witj path: " + propertyFilePath);
        }
    }

    public String get(String value){
        return property.getProperty(value);
    }

}