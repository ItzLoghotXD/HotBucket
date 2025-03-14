package me.itzloghotxd.hotbucket.event.raid;

import me.itzloghotxd.hotbucket.Raid;
import me.itzloghotxd.hotbucket.World;
import me.itzloghotxd.hotbucket.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a {@link Raid} is stopped.
 */
public class RaidStopEvent extends RaidEvent {

    private static final HandlerList handlers = new HandlerList();
    //
    private final Reason reason;

    public RaidStopEvent(@NotNull Raid raid, @NotNull World world, @NotNull Reason reason) {
        super(raid, world);
        this.reason = reason;
    }

    /**
     * Returns the stop reason.
     *
     * @return Reason
     */
    @NotNull
    public Reason getReason() {
        return reason;
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

    public enum Reason {

        /**
         * Because the difficulty has been changed to peaceful.
         */
        PEACE,
        /**
         * The raid took a long time without a final result.
         */
        TIMEOUT,
        /**
         * Finished the raid.
         */
        FINISHED,
        /**
         * Couldn't find a suitable place to spawn raiders.
         */
        UNSPAWNABLE,
        /**
         * The place where the raid occurs no longer be a village.
         */
        NOT_IN_VILLAGE
    }
}
