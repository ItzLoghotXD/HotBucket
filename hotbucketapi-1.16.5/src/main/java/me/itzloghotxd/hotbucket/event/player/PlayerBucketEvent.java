package me.itzloghotxd.hotbucket.event.player;

import me.itzloghotxd.hotbucket.Material;
import me.itzloghotxd.hotbucket.block.Block;
import me.itzloghotxd.hotbucket.block.BlockFace;
import me.itzloghotxd.hotbucket.entity.Player;
import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called when a player interacts with a Bucket
 */
public abstract class PlayerBucketEvent extends PlayerEvent implements Cancellable {
    private ItemStack itemStack;
    private boolean cancelled = false;
    private final Block block;
    private final Block blockClicked;
    private final BlockFace blockFace;
    private final Material bucket;

    @Deprecated
    public PlayerBucketEvent(@NotNull final Player who, @NotNull final Block blockClicked, @NotNull final BlockFace blockFace, @NotNull final Material bucket, @NotNull final ItemStack itemInHand) {
        this(who, null, blockClicked.getRelative(blockFace), blockFace, bucket, itemInHand);
    }

    public PlayerBucketEvent(@NotNull final Player who, @NotNull final Block block, @NotNull final Block blockClicked, @NotNull final BlockFace blockFace, @NotNull final Material bucket, @NotNull final ItemStack itemInHand) {
        super(who);
        this.block = block;
        this.blockClicked = blockClicked;
        this.blockFace = blockFace;
        this.itemStack = itemInHand;
        this.bucket = bucket;
    }

    /**
     * Returns the bucket used in this event
     *
     * @return the used bucket
     */
    @NotNull
    public Material getBucket() {
        return bucket;
    }

    /**
     * Get the resulting item in hand after the bucket event
     *
     * @return ItemStack hold in hand after the event.
     */
    @Nullable
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * Set the item in hand after the event
     *
     * @param itemStack the new held ItemStack after the bucket event.
     */
    public void setItemStack(@Nullable ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Gets the block involved in this event.
     *
     * @return The Block which block is involved in this event
     */
    @NotNull
    public final Block getBlock() {
        return block;
    }

    /**
     * Return the block clicked
     *
     * @return the clicked block
     */
    @NotNull
    public Block getBlockClicked() {
        return blockClicked;
    }

    /**
     * Get the face on the clicked block
     *
     * @return the clicked face
     */
    @NotNull
    public BlockFace getBlockFace() {
        return blockFace;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
