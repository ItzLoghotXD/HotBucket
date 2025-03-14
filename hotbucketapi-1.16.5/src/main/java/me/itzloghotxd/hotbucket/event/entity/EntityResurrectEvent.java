package me.itzloghotxd.hotbucket.event.entity;

import me.itzloghotxd.hotbucket.entity.LivingEntity;
import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called when an entity dies and may have the opportunity to be resurrected.
 * Will be called in a cancelled state if the entity does not have a totem
 * equipped.
 */
public class EntityResurrectEvent extends EntityEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    //
    private boolean cancelled;

    public EntityResurrectEvent(@NotNull LivingEntity what) {
        super(what);
    }

    @NotNull
    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) entity;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
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
