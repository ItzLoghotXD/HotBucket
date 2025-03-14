package me.itzloghotxd.hotbucket.event.entity;

import me.itzloghotxd.hotbucket.block.Block;
import me.itzloghotxd.hotbucket.entity.Entity;
import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called when an {@link Entity} enters a block and is stored in that block.
 * <p>
 * This event is called for bees entering a bee hive.
 * <br>
 * It is not called when a silverfish "enters" a stone block. For that listen to
 * the {@link EntityChangeBlockEvent}.
 */
public class EntityEnterBlockEvent extends EntityEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final Block block;
    private boolean cancel;

    public EntityEnterBlockEvent(@NotNull final Entity entity, @NotNull final Block block) {
        super(entity);

        this.block = block;
    }

    /**
     * Get the block the entity will enter.
     *
     * @return the block
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
