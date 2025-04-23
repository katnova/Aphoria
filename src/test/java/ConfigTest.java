import xyz.akat.util.ConfigLoader;

/**
 * A simple test class to demonstrate the usage of the ConfigLoader.
 * This class can be run to verify that the configuration system is working correctly.
 */
public class ConfigTest {
    public static void main(String[] args) {
        System.out.println("Testing ConfigLoader...");
        
        // Initialize the configuration
        boolean initResult = ConfigLoader.init();
        System.out.println("Initialization result: " + initResult);
        
        // Read some configuration values
        String appName = ConfigLoader.read("app.name");
        String appVersion = ConfigLoader.read("app.version");
        String discordToken = ConfigLoader.read("discord.token");
        String nonExistentKey = ConfigLoader.read("non.existent.key");
        
        // Print the results
        System.out.println("app.name: " + appName);
        System.out.println("app.version: " + appVersion);
        System.out.println("discord.token: " + discordToken);
        System.out.println("non.existent.key: " + nonExistentKey);
        
        System.out.println("ConfigLoader test completed.");
    }
}