package me.itzloghotxd.hotbucket.event.entity;

import me.itzloghotxd.hotbucket.entity.ThrownExpBottle;
import me.itzloghotxd.hotbucket.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a ThrownExpBottle hits and releases experience.
 */
public class ExpBottleEvent extends ProjectileHitEvent {
    private static final HandlerList handlers = new HandlerList();
    private int exp;
    private boolean showEffect = true;

    public ExpBottleEvent(@NotNull final ThrownExpBottle bottle, final int exp) {
        super(bottle);
        this.exp = exp;
    }

    @NotNull
    @Override
    public ThrownExpBottle getEntity() {
        return (ThrownExpBottle) entity;
    }

    /**
     * This method indicates if the particle effect should be shown.
     *
     * @return true if the effect will be shown, false otherwise
     */
    public boolean getShowEffect() {
        return this.showEffect;
    }

    /**
     * This method sets if the particle effect will be shown.
     * <p>
     * This does not change the experience created.
     *
     * @param showEffect true indicates the effect will be shown, false
     *     indicates no effect will be shown
     */
    public void setShowEffect(final boolean showEffect) {
        this.showEffect = showEffect;
    }

    /**
     * This method retrieves the amount of experience to be created.
     * <p>
     * The number indicates a total amount to be divided into orbs.
     *
     * @return the total amount of experience to be created
     */
    public int getExperience() {
        return exp;
    }

    /**
     * This method sets the amount of experience to be created.
     * <p>
     * The number indicates a total amount to be divided into orbs.
     *
     * @param exp the total amount of experience to be created
     */
    public void setExperience(final int exp) {
        this.exp = exp;
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
