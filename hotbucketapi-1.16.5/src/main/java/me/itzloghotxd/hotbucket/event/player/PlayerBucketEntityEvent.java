package me.itzloghotxd.hotbucket.event.player;

import me.itzloghotxd.hotbucket.Material;
import me.itzloghotxd.hotbucket.entity.Entity;
import me.itzloghotxd.hotbucket.entity.Player;
import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.event.HandlerList;
import me.itzloghotxd.hotbucket.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * This event is called whenever a player captures an entity in a bucket.
 */
public class PlayerBucketEntityEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final Entity entity;
    private final ItemStack originalBucket;
    private final ItemStack entityBucket;

    public PlayerBucketEntityEvent(@NotNull Player player, @NotNull Entity entity, @NotNull ItemStack originalBucket, @NotNull ItemStack entityBucket) {
        super(player);
        this.entity = entity;
        this.originalBucket = originalBucket;
        this.entityBucket = entityBucket;
    }

    /**
     * Gets the {@link Entity} being put into the bucket.
     *
     * @return The {@link Entity} being put into the bucket
     */
    @NotNull
    public Entity getEntity() {
        return entity;
    }

    /**
     * Gets the bucket used to capture the {@link Entity}.
     *
     * This refers to the bucket clicked with, eg {@link Material#WATER_BUCKET}.
     *
     * @return The used bucket
     */
    @NotNull
    public ItemStack getOriginalBucket() {
        return originalBucket;
    }

    /**
     * Gets the bucket that the {@link Entity} will be put into.
     *
     * This refers to the bucket with the entity, eg
     * {@link Material#PUFFERFISH_BUCKET}.
     *
     * @return The bucket that the {@link Entity} will be put into
     */
    @NotNull
    public ItemStack getEntityBucket() {
        return entityBucket;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
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
