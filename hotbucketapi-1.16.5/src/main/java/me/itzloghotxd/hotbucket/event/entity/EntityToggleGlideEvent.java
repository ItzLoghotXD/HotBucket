package me.itzloghotxd.hotbucket.event.entity;

import me.itzloghotxd.hotbucket.entity.LivingEntity;
import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Sent when an entity's gliding status is toggled with an Elytra.
 * Examples of when this event would be called:
 * <ul>
 *     <li>Player presses the jump key while in midair and using an Elytra</li>
 *     <li>Player lands on ground while they are gliding (with an Elytra)</li>
 * </ul>
 * This can be visually estimated by the animation in which a player turns horizontal.
 */
public class EntityToggleGlideEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private boolean cancel = false;
    private final boolean isGliding;

    public EntityToggleGlideEvent(@NotNull LivingEntity who, final boolean isGliding) {
        super(who);
        this.isGliding = isGliding;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    public boolean isGliding() {
        return isGliding;
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
