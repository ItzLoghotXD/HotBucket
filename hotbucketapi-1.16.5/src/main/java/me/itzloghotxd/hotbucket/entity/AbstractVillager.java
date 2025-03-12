package me.itzloghotxd.hotbucket.entity;

import me.itzloghotxd.hotbucket.inventory.Inventory;
import me.itzloghotxd.hotbucket.inventory.InventoryHolder;
import me.itzloghotxd.hotbucket.inventory.Merchant;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a villager NPC
 */
public interface AbstractVillager extends Breedable, NPC, InventoryHolder, Merchant {

    /**
     * Gets this villager's inventory.
     * <br>
     * Note that this inventory is not the Merchant inventory, rather, it is the
     * items that a villager might have collected (from harvesting crops, etc.)
     *
     * {@inheritDoc}
     */
    @NotNull
    @Override
    Inventory getInventory();
}
