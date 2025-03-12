package me.itzloghotxd.hotbucket.event.inventory;

import me.itzloghotxd.hotbucket.block.Block;
import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.event.HandlerList;
import me.itzloghotxd.hotbucket.event.block.BlockEvent;
import me.itzloghotxd.hotbucket.inventory.BrewerInventory;
import org.jetbrains.annotations.NotNull;

/**
 * Called when the brewing of the contents inside the Brewing Stand is
 * complete.
 */
public class BrewEvent extends BlockEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private BrewerInventory contents;
    private int fuelLevel;
    private boolean cancelled;

    public BrewEvent(@NotNull Block brewer, @NotNull BrewerInventory contents, int fuelLevel) {
        super(brewer);
        this.contents = contents;
        this.fuelLevel = fuelLevel;
    }

    /**
     * Gets the contents of the Brewing Stand.
     *
     * @return the contents
     */
    @NotNull
    public BrewerInventory getContents() {
        return contents;
    }

    /**
     * Gets the remaining fuel level.
     *
     * @return the remaining fuel
     */
    public int getFuelLevel() {
        return fuelLevel;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
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
