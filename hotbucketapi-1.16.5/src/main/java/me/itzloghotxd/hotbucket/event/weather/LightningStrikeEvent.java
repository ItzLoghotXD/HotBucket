package me.itzloghotxd.hotbucket.event.weather;

import me.itzloghotxd.hotbucket.World;
import me.itzloghotxd.hotbucket.entity.LightningStrike;
import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Stores data for lightning striking
 */
public class LightningStrikeEvent extends WeatherEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;
    private final LightningStrike bolt;
    private final Cause cause;

    @Deprecated
    public LightningStrikeEvent(@NotNull final World world, @NotNull final LightningStrike bolt) {
        this(world, bolt, Cause.UNKNOWN);
    }

    public LightningStrikeEvent(@NotNull final World world, @NotNull final LightningStrike bolt, @NotNull final Cause cause) {
        super(world);
        this.bolt = bolt;
        this.cause = cause;
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
     * Gets the bolt which is striking the earth.
     *
     * @return lightning entity
     */
    @NotNull
    public LightningStrike getLightning() {
        return bolt;
    }

    /**
     * Gets the cause of this lightning strike.
     *
     * @return strike cause
     */
    @NotNull
    public Cause getCause() {
        return cause;
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

    public enum Cause {
        /**
         * Triggered by the /summon command.
         */
        COMMAND,
        /**
         * Triggered by an enchanted trident.
         */
        TRIDENT,
        /**
         * Triggered by a skeleton horse trap.
         */
        TRAP,
        /**
         * Triggered by weather.
         */
        WEATHER,
        /**
         * Unknown trigger.
         */
        UNKNOWN;
    }
}
