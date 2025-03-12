package me.itzloghotxd.hotbucket.event.inventory;

import me.itzloghotxd.hotbucket.block.Block;
import me.itzloghotxd.hotbucket.event.block.BlockCookEvent;
import me.itzloghotxd.hotbucket.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Called when an ItemStack is successfully smelted in a furnace.
 */
public class FurnaceSmeltEvent extends BlockCookEvent {

    public FurnaceSmeltEvent(@NotNull final Block furnace, @NotNull final ItemStack source, @NotNull final ItemStack result) {
        super(furnace, source, result);
    }
}
