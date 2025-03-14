package me.itzloghotxd.hotbucket.entity;

import java.util.UUID;
import me.itzloghotxd.hotbucket.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a dropped item.
 */
public interface Item extends Entity {

    /**
     * Gets the item stack associated with this item drop.
     *
     * @return An item stack.
     */
    @NotNull
    public ItemStack getItemStack();

    /**
     * Sets the item stack associated with this item drop.
     *
     * @param stack An item stack.
     */
    public void setItemStack(@NotNull ItemStack stack);

    /**
     * Gets the delay before this Item is available to be picked up by players
     *
     * @return Remaining delay
     */
    public int getPickupDelay();

    /**
     * Sets the delay before this Item is available to be picked up by players
     *
     * @param delay New delay
     */
    public void setPickupDelay(int delay);

    /**
     * Sets the owner of this item.
     *
     * Other entities will not be able to pickup this item when an owner is set.
     *
     * @param owner UUID of new owner
     */
    public void setOwner(@Nullable UUID owner);

    /**
     * Get the owner of this item.
     *
     * @return UUID of owner
     */
    @Nullable
    public UUID getOwner();

    /**
     * Set the thrower of this item.
     *
     * The thrower is the entity which dropped the item. This affects the
     * trigger criteria for item pickups, for things such as advancements.
     *
     * @param uuid UUID of thrower
     */
    public void setThrower(@Nullable UUID uuid);

    /**
     * Get the thrower of this item.
     *
     * The thrower is the entity which dropped the item.
     *
     * @return UUID of thrower
     */
    @Nullable
    public UUID getThrower();
}
