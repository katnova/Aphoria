package xyz.akat;

import xyz.akat.util.ConfigLoader;
import xyz.akat.services.DiscordService;

public class Main {
    public static void main(String[] args) {
        // Initialize configuration
        if (!ConfigLoader.init()) {
            System.err.println("Failed to initialize configuration. Application may not function correctly.");
        }

        // Start Discord service
        new DiscordService();
    }
}
