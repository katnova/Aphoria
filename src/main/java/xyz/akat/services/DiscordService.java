package xyz.akat.services;

import discord4j.core.DiscordClient;
import xyz.akat.handlers.MessageHandlerRegister;
import xyz.akat.handlers.PongMessageHandler;
import xyz.akat.handlers.SillyMessageHandler;
import xyz.akat.util.ConfigLoader;

/**
 * A service that connects to Discord and handles message events.
 * 
 * <p>This class is responsible for:
 * <ul>
 *   <li>Connecting to Discord using a token from the configuration</li>
 *   <li>Setting up message handlers to respond to specific commands</li>
 *   <li>Processing incoming messages and routing them to the appropriate handlers</li>
 * </ul>
 * 
 * <p>When the service starts, it automatically registers the default message handlers
 * ({@link PongMessageHandler} and {@link SillyMessageHandler}) which respond to
 * specific commands in Discord channels.
 * 
 * <h2>How it works</h2>
 * <p>The service creates a Discord client using a token from the configuration file.
 * It then sets up a gateway connection and registers message handlers to process
 * incoming messages. Each message handler responds to a specific command (like "!ping").
 * 
 * <h2>How to register new message handlers</h2>
 * <p>To create and register a new message handler:
 * 
 * <ol>
 *   <li>Create a new class that extends {@link xyz.akat.handlers.AbstractMessageHandler}</li>
 *   <li>In the constructor, call the parent constructor with the command string 
 *       (e.g., <code>super("!mycommand");</code>)</li>
 *   <li>Override the <code>onMessage</code> method to define what happens when the command is received</li>
 *   <li>Add your new handler to the registration chain in the DiscordService constructor:
 *       <pre>
 *       .register(new PongMessageHandler())
 *       .register(new SillyMessageHandler())
 *       .register(new YourNewMessageHandler())  // Add your handler here
 *       </pre>
 *   </li>
 * </ol>
 * 
 * <p>Example of a simple message handler:
 * <pre>
 * public class GreetingMessageHandler extends AbstractMessageHandler {
 *     public GreetingMessageHandler() {
 *         super("!hello");
 *     }
 *     
 *     &#64;Override
 *     public void onMessage(Message message) {
 *         super.replyToMessageInChannel("Hello there!");
 *     }
 * }
 * </pre>
 */
public class DiscordService {
    /**
     * The Discord client instance used to connect to Discord.
     * This is initialized in the constructor using the token from configuration.
     */
    private DiscordClient client;

    /**
     * Constructs a new DiscordService and connects to Discord.
     * 
     * <p>This constructor:
     * <ol>
     *   <li>Reads the Discord token from the configuration file</li>
     *   <li>Creates a Discord client with the token</li>
     *   <li>Sets up a gateway connection</li>
     *   <li>Registers the default message handlers</li>
     * </ol>
     * 
     * <p>The service starts processing messages as soon as it's constructed.
     * No additional steps are needed to activate the service.
     */
    public DiscordService() {
        // Get the Discord token from configuration
        String token = ConfigLoader.read("discord.token");

        DiscordClient.create(token)
                .withGateway(client -> new MessageHandlerRegister(client)
                        .register(new PongMessageHandler())
                        .register(new SillyMessageHandler())
                        .getProvider())
                .block();
    }
}
