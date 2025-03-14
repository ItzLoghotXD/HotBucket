
package me.itzloghotxd.hotbucket.event.inventory;

import me.itzloghotxd.hotbucket.entity.HumanEntity;
import me.itzloghotxd.hotbucket.event.HandlerList;
import me.itzloghotxd.hotbucket.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a player related inventory event
 */
public class InventoryCloseEvent extends InventoryEvent {
    private static final HandlerList handlers = new HandlerList();

    public InventoryCloseEvent(@NotNull InventoryView transaction) {
        super(transaction);
    }

    /**
     * Returns the player involved in this event
     *
     * @return Player who is involved in this event
     */
    @NotNull
    public final HumanEntity getPlayer() {
        return transaction.getPlayer();
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
