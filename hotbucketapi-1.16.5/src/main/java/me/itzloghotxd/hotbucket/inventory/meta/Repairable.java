package me.itzloghotxd.hotbucket.inventory.meta;

import org.jetbrains.annotations.NotNull;

/**
 * Represents an item that can be repaired at an anvil.
 */
public interface Repairable {

    /**
     * Checks to see if this has a repair penalty
     *
     * @return true if this has a repair penalty
     */
    boolean hasRepairCost();

    /**
     * Gets the repair penalty
     *
     * @return the repair penalty
     */
    int getRepairCost();

    /**
     * Sets the repair penalty
     *
     * @param cost repair penalty
     */
    void setRepairCost(int cost);

    @SuppressWarnings("javadoc")
    @NotNull
    Repairable clone();
}
