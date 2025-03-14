package me.itzloghotxd.hotbucket.event.entity;

import java.util.Collections;
import me.itzloghotxd.hotbucket.entity.Entity;
import me.itzloghotxd.hotbucket.entity.LightningStrike;
import me.itzloghotxd.hotbucket.entity.Pig;
import me.itzloghotxd.hotbucket.entity.PigZombie;
import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Stores data for pigs being zapped
 */
public class PigZapEvent extends EntityTransformEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;
    private final PigZombie pigzombie;
    private final LightningStrike bolt;

    public PigZapEvent(@NotNull final Pig pig, @NotNull final LightningStrike bolt, @NotNull final PigZombie pigzombie) {
        super(pig, Collections.singletonList((Entity) pigzombie), TransformReason.LIGHTNING);
        this.bolt = bolt;
        this.pigzombie = pigzombie;
    }

    @Override
    public boolean isCancelled() {
        return canceled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        canceled = cancel;
    }

    @NotNull
    @Override
    public Pig getEntity() {
        return (Pig) entity;
    }

    /**
     * Gets the bolt which is striking the pig.
     *
     * @return lightning entity
     */
    @NotNull
    public LightningStrike getLightning() {
        return bolt;
    }

    /**
     * Gets the zombie pig that will replace the pig, provided the event is
     * not cancelled first.
     *
     * @return resulting entity
     * @deprecated use {@link EntityTransformEvent#getTransformedEntity()}
     */
    @NotNull
    @Deprecated
    public PigZombie getPigZombie() {
        return pigzombie;
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
