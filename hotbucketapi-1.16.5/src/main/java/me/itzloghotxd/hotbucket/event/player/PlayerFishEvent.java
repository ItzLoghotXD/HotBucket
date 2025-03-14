package me.itzloghotxd.hotbucket.event.player;

import me.itzloghotxd.hotbucket.entity.Entity;
import me.itzloghotxd.hotbucket.entity.FishHook;
import me.itzloghotxd.hotbucket.entity.Player;
import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Thrown when a player is fishing
 */
public class PlayerFishEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Entity entity;
    private boolean cancel = false;
    private int exp;
    private final State state;
    private final FishHook hookEntity;

    public PlayerFishEvent(@NotNull final Player player, @Nullable final Entity entity, @NotNull final FishHook hookEntity, @NotNull final State state) {
        super(player);
        this.entity = entity;
        this.hookEntity = hookEntity;
        this.state = state;
    }

    /**
     * Gets the entity caught by the player.
     * <p>
     * If player has fished successfully, the result may be cast to {@link
     * me.itzloghotxd.hotbucket.entity.Item}.
     *
     * @return Entity caught by the player, Entity if fishing, and null if
     *     bobber has gotten stuck in the ground or nothing has been caught
     */
    @Nullable
    public Entity getCaught() {
        return entity;
    }

    /**
     * Gets the fishing hook.
     *
     * @return the entity representing the fishing hook/bobber.
     */
    @NotNull
    public FishHook getHook() {
        return hookEntity;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Gets the amount of experience received when fishing.
     * <p>
     * Note: This value has no default effect unless the event state is {@link
     * State#CAUGHT_FISH}.
     *
     * @return the amount of experience to drop
     */
    public int getExpToDrop() {
        return exp;
    }

    /**
     * Sets the amount of experience received when fishing.
     * <p>
     * Note: This value has no default effect unless the event state is {@link
     * State#CAUGHT_FISH}.
     *
     * @param amount the amount of experience to drop
     */
    public void setExpToDrop(int amount) {
        exp = amount;
    }

    /**
     * Gets the state of the fishing
     *
     * @return A State detailing the state of the fishing
     */
    @NotNull
    public State getState() {
        return state;
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

    /**
     * An enum to specify the state of the fishing
     */
    public enum State {

        /**
         * When a player is fishing, ie casting the line out.
         */
        FISHING,
        /**
         * When a player has successfully caught a fish and is reeling it in. In
         * this instance, a "fish" is any item retrieved from water as a result
         * of fishing, ie an item, but not necessarily a fish.
         */
        CAUGHT_FISH,
        /**
         * When a player has successfully caught an entity. This refers to any
         * already spawned entity in the world that has been hooked directly by
         * the rod.
         */
        CAUGHT_ENTITY,
        /**
         * When a bobber is stuck in the ground.
         */
        IN_GROUND,
        /**
         * When a player fails to catch a bite while fishing usually due to
         * poor timing.
         */
        FAILED_ATTEMPT,
        /**
         * When a player reels in their hook without receiving any bites.
         */
        REEL_IN,
        /**
         * Called when there is a bite on the hook and it is ready to be reeled
         * in.
         */
        BITE
    }
}
