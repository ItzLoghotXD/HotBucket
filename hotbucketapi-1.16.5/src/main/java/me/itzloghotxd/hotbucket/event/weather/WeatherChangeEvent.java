package me.itzloghotxd.hotbucket.event.weather;

import me.itzloghotxd.hotbucket.World;
import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Stores data for weather changing in a world
 */
public class WeatherChangeEvent extends WeatherEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;
    private final boolean to;

    public WeatherChangeEvent(@NotNull final World world, final boolean to) {
        super(world);
        this.to = to;
    }

    @Override
    public boolean isCancelled() {
        return canceled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        canceled = cancel;
    }

    /**
     * Gets the state of weather that the world is being set to
     *
     * @return true if the weather is being set to raining, false otherwise
     */
    public boolean toWeatherState() {
        return to;
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
