package me.itzloghotxd.hotbucket.entity.minecart;

import me.itzloghotxd.hotbucket.entity.Minecart;
import me.itzloghotxd.hotbucket.inventory.InventoryHolder;
import me.itzloghotxd.hotbucket.loot.Lootable;

/**
 * Represents a minecart with a chest. These types of {@link Minecart
 * minecarts} have their own inventory that can be accessed using methods
 * from the {@link InventoryHolder} interface.
 */
public interface StorageMinecart extends Minecart, InventoryHolder, Lootable {
}
