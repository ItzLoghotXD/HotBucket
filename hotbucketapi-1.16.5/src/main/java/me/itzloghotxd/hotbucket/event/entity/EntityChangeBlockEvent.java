package me.itzloghotxd.hotbucket.event.entity;

import me.itzloghotxd.hotbucket.Material;
import me.itzloghotxd.hotbucket.block.Block;
import me.itzloghotxd.hotbucket.block.data.BlockData;
import me.itzloghotxd.hotbucket.entity.Entity;
import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called when any Entity changes a block and a more specific event is not available.
 */
public class EntityChangeBlockEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Block block;
    private boolean cancel;
    private final BlockData to;

    public EntityChangeBlockEvent(@NotNull final Entity what, @NotNull final Block block, @NotNull final BlockData to) {
        super(what);
        this.block = block;
        this.cancel = false;
        this.to = to;
    }

    /**
     * Gets the block the entity is changing
     *
     * @return the block that is changing
     */
    @NotNull
    public Block getBlock() {
        return block;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Gets the Material that the block is changing into
     *
     * @return the material that the block is changing into
     */
    @NotNull
    public Material getTo() {
        return to.getMaterial();
    }

    /**
     * Gets the data for the block that would be changed into
     *
     * @return the data for the block that would be changed into
     */
    @NotNull
    public BlockData getBlockData() {
        return to;
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
