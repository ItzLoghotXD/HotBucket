package me.itzloghotxd.hotbucket.event.player;

import me.itzloghotxd.hotbucket.entity.Player;
import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.event.HandlerList;
import me.itzloghotxd.hotbucket.util.Vector;
import org.jetbrains.annotations.NotNull;

/**
 * Called when the velocity of a player changes.
 */
public class PlayerVelocityEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private Vector velocity;

    public PlayerVelocityEvent(@NotNull final Player player, @NotNull final Vector velocity) {
        super(player);
        this.velocity = velocity;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Gets the velocity vector that will be sent to the player
     *
     * @return Vector the player will get
     */
    @NotNull
    public Vector getVelocity() {
        return velocity;
    }

    /**
     * Sets the velocity vector in meters per tick that will be sent to the player
     *
     * @param velocity The velocity vector that will be sent to the player
     */
    public void setVelocity(@NotNull Vector velocity) {
        this.velocity = velocity;
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
