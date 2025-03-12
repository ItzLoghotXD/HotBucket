package me.itzloghotxd.hotbucket.plugin;

import me.itzloghotxd.hotbucket.event.Event;
import me.itzloghotxd.hotbucket.event.EventException;
import me.itzloghotxd.hotbucket.event.Listener;
import org.jetbrains.annotations.NotNull;

/**
 * Interface which defines the class for event call backs to plugins
 */
public interface EventExecutor {
    public void execute(@NotNull Listener listener, @NotNull Event event) throws EventException;
}
