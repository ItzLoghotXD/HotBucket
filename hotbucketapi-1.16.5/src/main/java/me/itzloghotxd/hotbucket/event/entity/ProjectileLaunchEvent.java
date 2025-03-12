package me.itzloghotxd.hotbucket.event.entity;

import me.itzloghotxd.hotbucket.entity.Entity;
import me.itzloghotxd.hotbucket.entity.Projectile;
import me.itzloghotxd.hotbucket.event.Cancellable;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a projectile is launched.
 */
public class ProjectileLaunchEvent extends EntitySpawnEvent implements Cancellable {
    private boolean cancelled;

    public ProjectileLaunchEvent(@NotNull Entity what) {
        super(what);
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
    public Projectile getEntity() {
        return (Projectile) entity;
    }
}
