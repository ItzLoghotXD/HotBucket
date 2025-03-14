package me.itzloghotxd.hotbucket.event.block;

import me.itzloghotxd.hotbucket.block.Block;
import me.itzloghotxd.hotbucket.block.BlockFace;
import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Represents events with a source block and a destination block, currently
 * only applies to liquid (lava and water) and teleporting dragon eggs.
 * <p>
 * If a Block From To event is cancelled, the block will not move (the liquid
 * will not flow).
 */
public class BlockFromToEvent extends BlockEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    protected Block to;
    protected BlockFace face;
    protected boolean cancel;

    public BlockFromToEvent(@NotNull final Block block, @NotNull final BlockFace face) {
        super(block);
        this.face = face;
        this.cancel = false;
    }

    public BlockFromToEvent(@NotNull final Block block, @NotNull final Block toBlock) {
        super(block);
        this.to = toBlock;
        this.face = BlockFace.SELF;
        this.cancel = false;
    }

    /**
     * Gets the BlockFace that the block is moving to.
     *
     * @return The BlockFace that the block is moving to
     */
    @NotNull
    public BlockFace getFace() {
        return face;
    }

    /**
     * Convenience method for getting the faced Block.
     *
     * @return The faced Block
     */
    @NotNull
    public Block getToBlock() {
        if (to == null) {
            to = block.getRelative(face);
        }
        return to;
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
