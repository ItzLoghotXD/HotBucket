package me.itzloghotxd.hotbucket.event.player;

import me.itzloghotxd.hotbucket.entity.Item;
import me.itzloghotxd.hotbucket.entity.Player;
import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Thrown when a player drops an item from their inventory
 */
public class PlayerDropItemEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Item drop;
    private boolean cancel = false;

    public PlayerDropItemEvent(@NotNull final Player player, @NotNull final Item drop) {
        super(player);
        this.drop = drop;
    }

    /**
     * Gets the ItemDrop created by the player
     *
     * @return ItemDrop created by the player
     */
    @NotNull
    public Item getItemDrop() {
        return drop;
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
