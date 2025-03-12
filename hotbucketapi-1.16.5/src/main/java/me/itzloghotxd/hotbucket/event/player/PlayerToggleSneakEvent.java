package me.itzloghotxd.hotbucket.event.player;

import me.itzloghotxd.hotbucket.entity.Player;
import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a player toggles their sneaking state
 */
public class PlayerToggleSneakEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final boolean isSneaking;
    private boolean cancel = false;

    public PlayerToggleSneakEvent(@NotNull final Player player, final boolean isSneaking) {
        super(player);
        this.isSneaking = isSneaking;
    }

    /**
     * Returns whether the player is now sneaking or not.
     *
     * @return sneaking state
     */
    public boolean isSneaking() {
        return isSneaking;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
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
