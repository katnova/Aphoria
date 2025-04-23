package xyz.akat.handlers;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

/**
 * Abstract generic register for Discord event handlers.
 * This class provides a framework for registering and managing handlers for specific Discord events.
 * It sets up an event listener on the Discord client and routes events to the appropriate handlers.
 *
 * @param <T> The type of Discord event this register handles (must extend Event)
 * @param <R> The type of handler this register manages
 */
public abstract class AbstractGenericRegister<T extends Event, R> {
    /**
     * List of registered handlers for the specific event type.
     */
    private ArrayList<R> HANDLERS = new ArrayList<>();

    /**
     * The event provider that listens for events of type T and routes them to handlers.
     */
    private Flux<Object> provider;

    /**
     * Constructs a new register for the specified Discord client and event type.
     * Sets up an event listener that will call the abstract seekHandler method
     * whenever an event of the specified type occurs.
     *
     * @param client The Discord client to listen for events on
     * @param eventClass The class of events to listen for
     */
    public AbstractGenericRegister(GatewayDiscordClient client, Class<T> eventClass) {
        this.provider = client.on(eventClass, event -> {
            seekHandler(event);
            return Mono.empty();
        });
    }

    /**
     * Gets the event provider that this register is listening on.
     *
     * @return The Flux event provider
     */
    public Flux<Object> getProvider() {
        return provider;
    }

    /**
     * Registers a new handler for events.
     * The handler will be called when an event of type T occurs.
     *
     * @param handler The handler to register
     * @return This register instance for method chaining
     */
    public AbstractGenericRegister<T, R> register(R handler) {
        log("Registering handler: " + handler.getClass().getSimpleName());
        HANDLERS.add(handler);
        return this;
    }

    /**
     * Gets the list of registered handlers.
     *
     * @return The list of handlers
     */
    protected ArrayList<R> getHandlers() {
        return HANDLERS;
    }

    /**
     * Logs a message to the console with the class name as a prefix.
     *
     * @param message The message to log
     */
    public void log(String message) {
        System.out.println("[" + this.getClass().getSimpleName() + "] " + message);
    }

    /**
     * Abstract method that must be implemented by subclasses to define
     * how events are routed to the appropriate handlers.
     *
     * @param t The event to process
     */
    protected abstract void seekHandler(T t);
}
