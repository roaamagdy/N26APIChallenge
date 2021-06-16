package com.n26.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Roaa
 * This class is responsible in configuring/loading the configuration file
 */
public class ConfigManager {
    private static final Properties prop = new Properties();
    private static ConfigManager configManager;

    private ConfigManager() {
        try {
            InputStream stream = ConfigManager.class.getResourceAsStream("/config.properties");
            prop.load(stream);
        } catch (FileNotFoundException e) {
            System.out.println("File Not found exception :  " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException in reading properties file :  " + e.getMessage());
        }
    }

    public static ConfigManager getInstance() {
        if (configManager == null) {
            synchronized (ConfigManager.class) {
                configManager = new ConfigManager();
            }
        }
        return configManager;
    }

    public String getString(String key) {

        return System.getProperty(key, prop.getProperty(key));
    }

}
