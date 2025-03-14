package me.itzloghotxd.hotbucket.event.entity;

import java.util.List;
import me.itzloghotxd.hotbucket.PortalType;
import me.itzloghotxd.hotbucket.block.BlockState;
import me.itzloghotxd.hotbucket.entity.LivingEntity;
import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.event.HandlerList;
import me.itzloghotxd.hotbucket.event.world.PortalCreateEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Thrown when a Living Entity creates a portal in a world.
 *
 * @deprecated Use {@link PortalCreateEvent}
 */
@Deprecated
public class EntityCreatePortalEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final List<BlockState> blocks;
    private boolean cancelled = false;
    private PortalType type = PortalType.CUSTOM;

    public EntityCreatePortalEvent(@NotNull final LivingEntity what, @NotNull final List<BlockState> blocks, @NotNull final PortalType type) {
        super(what);

        this.blocks = blocks;
        this.type = type;
    }

    @NotNull
    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) entity;
    }

    /**
     * Gets a list of all blocks associated with the portal.
     *
     * @return List of blocks that will be changed.
     */
    @NotNull
    public List<BlockState> getBlocks() {
        return blocks;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    /**
     * Gets the type of portal that is trying to be created.
     *
     * @return Type of portal.
     */
    @NotNull
    public PortalType getPortalType() {
        return type;
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
