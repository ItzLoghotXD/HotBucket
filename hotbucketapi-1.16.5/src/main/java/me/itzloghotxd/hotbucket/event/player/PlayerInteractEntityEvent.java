package me.itzloghotxd.hotbucket.event.player;

import me.itzloghotxd.hotbucket.entity.Entity;
import me.itzloghotxd.hotbucket.entity.Player;
import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.event.HandlerList;
import me.itzloghotxd.hotbucket.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that is called when a player right clicks an entity.
 */
public class PlayerInteractEntityEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    protected Entity clickedEntity;
    boolean cancelled = false;
    private EquipmentSlot hand;

    public PlayerInteractEntityEvent(@NotNull final Player who, @NotNull final Entity clickedEntity) {
        this(who, clickedEntity, EquipmentSlot.HAND);
    }

    public PlayerInteractEntityEvent(@NotNull final Player who, @NotNull final Entity clickedEntity, @NotNull final EquipmentSlot hand) {
        super(who);
        this.clickedEntity = clickedEntity;
        this.hand = hand;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    /**
     * Gets the entity that was right-clicked by the player.
     *
     * @return entity right clicked by player
     */
    @NotNull
    public Entity getRightClicked() {
        return this.clickedEntity;
    }

    /**
     * The hand used to perform this interaction.
     *
     * @return the hand used to interact
     */
    @NotNull
    public EquipmentSlot getHand() {
        return hand;
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
