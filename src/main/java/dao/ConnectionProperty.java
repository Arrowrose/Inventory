package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConnectionProperty {
    
    public static final String CONFIG_NAME = "config.properties";
    public static final Properties PROPERTY_CONFIG = new Properties();
    
    public ConnectionProperty() throws FileNotFoundException, IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        PROPERTY_CONFIG.load(classLoader.getResourceAsStream("config/" + CONFIG_NAME));
    }
    
    public static String getProperty(String property) {
        return PROPERTY_CONFIG.getProperty(property);
    }
}