package me.itzloghotxd.hotbucket.event.world;

import me.itzloghotxd.hotbucket.Chunk;
import me.itzloghotxd.hotbucket.event.HandlerList;
import me.itzloghotxd.hotbucket.generator.BlockPopulator;
import org.jetbrains.annotations.NotNull;

/**
 * Thrown when a new chunk has finished being populated.
 * <p>
 * If your intent is to populate the chunk using this event, please see {@link
 * BlockPopulator}
 */
public class ChunkPopulateEvent extends ChunkEvent {
    private static final HandlerList handlers = new HandlerList();

    public ChunkPopulateEvent(@NotNull final Chunk chunk) {
        super(chunk);
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
