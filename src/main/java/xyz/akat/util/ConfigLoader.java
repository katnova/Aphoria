package xyz.akat.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A static utility class for loading and accessing configuration properties.
 * This class provides methods to initialize and read properties from a configuration file.
 */
public class ConfigLoader {
    private static Properties properties = new Properties();
    private static boolean initialized = false;

    /**
     * Initializes the ConfigLoader by loading properties from the config.properties file.
     * This method should be called before any calls to the read method.
     * 
     * @return true if initialization was successful, false otherwise
     */
    public static boolean init() {
        if (initialized) {
            return true;
        }

        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Unable to find config.properties");
                return false;
            }
            properties.load(input);
            initialized = true;
            return true;
        } catch (IOException ex) {
            System.out.println("Error reading properties file: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Reads a property value from the configuration.
     * If the ConfigLoader has not been initialized, this method will attempt to initialize it.
     * 
     * @param key the property key to read
     * @return the property value, or null if the key is not found or initialization failed
     */
    public static String read(String key) {
        if (!initialized && !init()) {
            return null;
        }
        return properties.getProperty(key);
    }
}