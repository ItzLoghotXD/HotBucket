package me.itzloghotxd.hotbucket.event.server;

import me.itzloghotxd.hotbucket.event.HandlerList;
import me.itzloghotxd.hotbucket.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.NotNull;

/**
 * This event is called when a service is unregistered.
 * <p>
 * Warning: The order in which register and unregister events are called
 * should not be relied upon.
 */
public class ServiceUnregisterEvent extends ServiceEvent {
    private static final HandlerList handlers = new HandlerList();

    public ServiceUnregisterEvent(@NotNull RegisteredServiceProvider<?> serviceProvider) {
        super(serviceProvider);
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
