package me.itzloghotxd.hotbucket.event.world;

import me.itzloghotxd.hotbucket.World;
import me.itzloghotxd.hotbucket.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a World is saved.
 */
public class WorldSaveEvent extends WorldEvent {
    private static final HandlerList handlers = new HandlerList();

    public WorldSaveEvent(@NotNull final World world) {
        super(world);
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
