package me.itzloghotxd.hotbucket.event.server;

import me.itzloghotxd.hotbucket.event.HandlerList;
import me.itzloghotxd.hotbucket.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a plugin is disabled.
 */
public class PluginDisableEvent extends PluginEvent {
    private static final HandlerList handlers = new HandlerList();

    public PluginDisableEvent(@NotNull final Plugin plugin) {
        super(plugin);
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
