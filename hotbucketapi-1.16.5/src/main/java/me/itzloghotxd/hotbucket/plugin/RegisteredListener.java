package me.itzloghotxd.hotbucket.plugin;

import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.event.Event;
import me.itzloghotxd.hotbucket.event.EventException;
import me.itzloghotxd.hotbucket.event.EventPriority;
import me.itzloghotxd.hotbucket.event.Listener;
import org.jetbrains.annotations.NotNull;

/**
 * Stores relevant information for plugin listeners
 */
public class RegisteredListener {
    private final Listener listener;
    private final EventPriority priority;
    private final Plugin plugin;
    private final EventExecutor executor;
    private final boolean ignoreCancelled;

    public RegisteredListener(@NotNull final Listener listener, @NotNull final EventExecutor executor, @NotNull final EventPriority priority, @NotNull final Plugin plugin, final boolean ignoreCancelled) {
        this.listener = listener;
        this.priority = priority;
        this.plugin = plugin;
        this.executor = executor;
        this.ignoreCancelled = ignoreCancelled;
    }

    /**
     * Gets the listener for this registration
     *
     * @return Registered Listener
     */
    @NotNull
    public Listener getListener() {
        return listener;
    }

    /**
     * Gets the plugin for this registration
     *
     * @return Registered Plugin
     */
    @NotNull
    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * Gets the priority for this registration
     *
     * @return Registered Priority
     */
    @NotNull
    public EventPriority getPriority() {
        return priority;
    }

    /**
     * Calls the event executor
     *
     * @param event The event
     * @throws EventException If an event handler throws an exception.
     */
    public void callEvent(@NotNull final Event event) throws EventException {
        if (event instanceof Cancellable) {
            if (((Cancellable) event).isCancelled() && isIgnoringCancelled()) {
                return;
            }
        }
        executor.execute(listener, event);
    }

    /**
     * Whether this listener accepts cancelled events
     *
     * @return True when ignoring cancelled events
     */
    public boolean isIgnoringCancelled() {
        return ignoreCancelled;
    }
}
