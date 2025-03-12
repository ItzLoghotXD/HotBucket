package me.itzloghotxd.hotbucket.event.block;

import me.itzloghotxd.hotbucket.block.Block;
import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called when leaves are decaying naturally.
 * <p>
 * If a Leaves Decay event is cancelled, the leaves will not decay.
 */
public class LeavesDecayEvent extends BlockEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;

    public LeavesDecayEvent(@NotNull final Block block) {
        super(block);
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
