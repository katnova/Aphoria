package xyz.akat.handlers;

import discord4j.core.object.entity.Message;

/**
 * Abstract base class for handling Discord message commands.
 * This class provides the framework for creating command-based message handlers
 * that respond to specific command strings in Discord messages.
 * 
 * Subclasses must implement the {@link #onMessage(Message)} method to define
 * the specific behavior when a matching command is received.
 */
public abstract class AbstractMessageHandler {
    /**
     * The command string that this handler responds to.
     * Messages with content matching this command (case-insensitive) will be processed.
     */
    private final String command;

    /**
     * The most recent message that matched this handler's command.
     * Used for replying to the message in the same channel.
     */
    private Message message;

    /**
     * Constructs a new message handler for the specified command.
     *
     * @param command The command string that this handler will respond to
     */
    public AbstractMessageHandler(String command) {
        this.command = command;
    }

    /**
     * Processes a Discord message and determines if it matches this handler's command.
     * If the message content matches the command (case-insensitive), it stores the message
     * and calls the {@link #onMessage(Message)} method. Otherwise, it logs that the message
     * is being ignored.
     *
     * @param message The Discord message to process
     */
    protected void handle(Message message) {
        final boolean shouldHandle = message.getContent().equalsIgnoreCase(command);

        if (shouldHandle) {
            this.message = message;
            onMessage(message);
        } else {
            log("Ignoring, message '" + message.getContent() + "' does not match the command ('" + command + "') for this handler.");
        }
    }

    /**
     * Sends a reply to the channel where the most recent matching message was received.
     * This method uses the stored message reference to determine the channel.
     *
     * @param reply The text content to send as a reply
     */
    protected void replyToMessageInChannel(String reply) {
        log("Replying to message: " + reply);
        message.getChannel().flatMap(channel -> channel.createMessage(reply)).block();
    }

    /**
     * Logs a message to the console with the class name as a prefix.
     *
     * @param message The message to log
     */
    protected void log(String message) {
        System.out.println("[" + this.getClass().getSimpleName() + "] " + message);
    }

    /**
     * Abstract method that must be implemented by subclasses to define
     * the specific behavior when a matching command is received.
     *
     * @param message The Discord message that matched this handler's command
     */
    abstract void onMessage(Message message);
}
