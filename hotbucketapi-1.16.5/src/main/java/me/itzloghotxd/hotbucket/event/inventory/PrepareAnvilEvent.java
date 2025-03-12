package me.itzloghotxd.hotbucket.event.inventory;

import me.itzloghotxd.hotbucket.event.HandlerList;
import me.itzloghotxd.hotbucket.inventory.AnvilInventory;
import me.itzloghotxd.hotbucket.inventory.InventoryView;
import me.itzloghotxd.hotbucket.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called when an item is put in a slot for repair by an anvil.
 */
public class PrepareAnvilEvent extends InventoryEvent {

    private static final HandlerList handlers = new HandlerList();
    private ItemStack result;

    public PrepareAnvilEvent(@NotNull InventoryView inventory, @Nullable ItemStack result) {
        super(inventory);
        this.result = result;
    }

    @NotNull
    @Override
    public AnvilInventory getInventory() {
        return (AnvilInventory) super.getInventory();
    }

    /**
     * Get result item, may be null.
     *
     * @return result item
     */
    @Nullable
    public ItemStack getResult() {
        return result;
    }

    public void setResult(@Nullable ItemStack result) {
        this.result = result;
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
