package me.itzloghotxd.hotbucket.event.inventory;

import me.itzloghotxd.hotbucket.event.inventory.InventoryType.SlotType;
import me.itzloghotxd.hotbucket.inventory.CraftingInventory;
import me.itzloghotxd.hotbucket.inventory.InventoryView;
import me.itzloghotxd.hotbucket.inventory.Recipe;
import org.jetbrains.annotations.NotNull;

/**
 * Called when the recipe of an Item is completed inside a crafting matrix.
 */
public class CraftItemEvent extends InventoryClickEvent {
    private Recipe recipe;

    public CraftItemEvent(@NotNull Recipe recipe, @NotNull InventoryView what, @NotNull SlotType type, int slot, @NotNull ClickType click, @NotNull InventoryAction action) {
        super(what, type, slot, click, action);
        this.recipe = recipe;
    }

    public CraftItemEvent(@NotNull Recipe recipe, @NotNull InventoryView what, @NotNull SlotType type, int slot, @NotNull ClickType click, @NotNull InventoryAction action, int key) {
        super(what, type, slot, click, action, key);
        this.recipe = recipe;
    }

    /**
     * @return A copy of the current recipe on the crafting matrix.
     */
    @NotNull
    public Recipe getRecipe() {
        return recipe;
    }

    @NotNull
    @Override
    public CraftingInventory getInventory() {
        return (CraftingInventory) super.getInventory();
    }
}
