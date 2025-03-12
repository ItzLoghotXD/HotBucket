package me.itzloghotxd.hotbucket.event.player;

import java.util.List;
import me.itzloghotxd.hotbucket.block.Block;
import me.itzloghotxd.hotbucket.entity.Player;
import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.event.HandlerList;
import me.itzloghotxd.hotbucket.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * This event is called whenever a player harvests a block.
 * <br>
 * A 'harvest' is when a block drops an item (usually some sort of crop) and
 * changes state, but is not broken in order to drop the item.
 * <br>
 * This event is not called for when a block is broken, to handle that, listen
 * for {@link me.itzloghotxd.hotbucket.event.block.BlockBreakEvent} and
 * {@link me.itzloghotxd.hotbucket.event.block.BlockDropItemEvent}.
 */
public class PlayerHarvestBlockEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private final Block harvestedBlock;
    private final List<ItemStack> itemsHarvested;

    public PlayerHarvestBlockEvent(@NotNull Player player, @NotNull Block harvestedBlock, @NotNull List<ItemStack> itemsHarvested) {
        super(player);
        this.harvestedBlock = harvestedBlock;
        this.itemsHarvested = itemsHarvested;
    }

    /**
     * Gets the block that is being harvested.
     *
     * @return The block that is being harvested
     */
    @NotNull
    public Block getHarvestedBlock() {
        return harvestedBlock;
    }

    /**
     * Gets a list of items that are being harvested from this block.
     *
     * @return A list of items that are being harvested from this block
     */
    @NotNull
    public List<ItemStack> getItemsHarvested() {
        return itemsHarvested;
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
