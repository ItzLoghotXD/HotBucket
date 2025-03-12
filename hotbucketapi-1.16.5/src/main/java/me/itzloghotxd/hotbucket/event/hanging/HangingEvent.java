package me.itzloghotxd.hotbucket.event.hanging;

import me.itzloghotxd.hotbucket.entity.Hanging;
import me.itzloghotxd.hotbucket.event.Event;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a hanging entity-related event.
 */
public abstract class HangingEvent extends Event {
    protected Hanging hanging;

    protected HangingEvent(@NotNull final Hanging painting) {
        this.hanging = painting;
    }

    /**
     * Gets the hanging entity involved in this event.
     *
     * @return the hanging entity
     */
    @NotNull
    public Hanging getEntity() {
        return hanging;
    }
}
