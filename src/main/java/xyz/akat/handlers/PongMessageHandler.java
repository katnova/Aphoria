package xyz.akat.handlers;

import discord4j.core.object.entity.Message;

/**
 * A message handler that responds with "Pong!" when a message containing the command "!ping" is received.
 * 
 * This handler extends {@link AbstractMessageHandler} and provides a simple implementation
 * that demonstrates the command-response pattern. It's useful for testing the bot's
 * responsiveness and connection status.
 * 
 * The handler listens specifically for the "!ping" command (case-insensitive) and
 * responds with "Pong!" in the same channel where the command was received.
 */
public class PongMessageHandler extends AbstractMessageHandler {

    /**
     * Constructs a new {@code PongMessageHandler} instance.
     * 
     * This handler is initialized to listen for messages containing the command "!ping".
     * When the command is detected, a response of "Pong!" is sent to the channel in which 
     * the message was received.
     * 
     * The constructor calls the parent class constructor {@link AbstractMessageHandler#AbstractMessageHandler(String)}
     * with the command string "!ping" to register this handler for that specific command.
     */
    public PongMessageHandler() {
        super("!ping");
    }

    /**
     * Processes an incoming message and sends a response of "Pong!" if the message matches the handler's command.
     * 
     * This method is called by the parent class {@link AbstractMessageHandler#handle(Message)} method
     * when a message containing the command "!ping" is received. It uses the 
     * {@link AbstractMessageHandler#replyToMessageInChannel(String)} method to send the response
     * back to the same channel where the command was received.
     * 
     * This implementation demonstrates a simple command-response pattern that can be used
     * as a template for more complex message handlers.
     *
     * @param message the incoming {@link Message} that triggered this handler
     */
    @Override
    public void onMessage(Message message) {
        super.replyToMessageInChannel("Pong!");
    }
}
