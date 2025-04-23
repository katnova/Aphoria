package xyz.akat.handlers;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;

/**
 * A specialized register for handling Discord message events.
 * This class extends AbstractGenericRegister to specifically handle MessageCreateEvent events
 * and route them to AbstractMessageHandler instances.
 * 
 * It provides the implementation for processing message events and distributing them
 * to all registered message handlers.
 */
public class MessageHandlerRegister extends AbstractGenericRegister<MessageCreateEvent, AbstractMessageHandler> {

    /**
     * Constructs a new MessageHandlerRegister for the specified Discord client.
     * Sets up an event listener for MessageCreateEvent events.
     *
     * @param client The Discord client to listen for message events on
     */
    public MessageHandlerRegister(GatewayDiscordClient client) {
        super(client, MessageCreateEvent.class);
    }

    /**
     * Processes a message event by passing it to all registered message handlers.
     * This implementation logs the message content and then calls the handle method
     * on each registered AbstractMessageHandler.
     *
     * @param event The message event to process
     */
    @Override
    protected void seekHandler(MessageCreateEvent event) {
        super.log("Seeking handler for message: " + event.getMessage().getContent());
        super.getHandlers().forEach(handler -> handler.handle(event.getMessage()));
    }
}
