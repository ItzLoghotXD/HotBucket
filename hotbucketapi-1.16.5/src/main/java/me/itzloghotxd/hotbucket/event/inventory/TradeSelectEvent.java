package me.itzloghotxd.hotbucket.event.inventory;

import me.itzloghotxd.hotbucket.event.HandlerList;
import me.itzloghotxd.hotbucket.inventory.InventoryView;
import me.itzloghotxd.hotbucket.inventory.Merchant;
import me.itzloghotxd.hotbucket.inventory.MerchantInventory;
import org.jetbrains.annotations.NotNull;

/**
 * This event is called whenever a player clicks a new trade on the trades
 * sidebar.
 * <p>
 * This event allows the user to get the index of the trade, letting them get
 * the MerchantRecipe via the Merchant.
 */
public class TradeSelectEvent extends InventoryInteractEvent {

    private static final HandlerList handlers = new HandlerList();
    //
    private final int index;

    public TradeSelectEvent(@NotNull InventoryView transaction, int newIndex) {
        super(transaction);
        this.index = newIndex;
    }

    /**
     * Used to get the index of the trade the player clicked on.
     *
     * @return The index of the trade clicked by the player
     */
    public int getIndex() {
        return index;
    }

    @NotNull
    @Override
    public MerchantInventory getInventory() {
        return (MerchantInventory) super.getInventory();
    }

    /**
     * Get the Merchant involved.
     *
     * @return the Merchant
     */
    @NotNull
    public Merchant getMerchant() {
        return getInventory().getMerchant();
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
