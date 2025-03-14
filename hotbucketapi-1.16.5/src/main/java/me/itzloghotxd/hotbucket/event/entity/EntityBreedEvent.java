package me.itzloghotxd.hotbucket.event.entity;

import org.apache.commons.lang.Validate;
import me.itzloghotxd.hotbucket.entity.LivingEntity;
import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.event.HandlerList;
import me.itzloghotxd.hotbucket.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called when one Entity breeds with another Entity.
 */
public class EntityBreedEvent extends EntityEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    //
    private final LivingEntity mother;
    private final LivingEntity father;
    private final LivingEntity breeder;
    private final ItemStack bredWith;
    private int experience;
    //
    private boolean cancel;

    public EntityBreedEvent(@NotNull LivingEntity child, @NotNull LivingEntity mother, @NotNull LivingEntity father, @Nullable LivingEntity breeder, @Nullable ItemStack bredWith, int experience) {
        super(child);

        Validate.notNull(child, "Cannot have null child");
        Validate.notNull(mother, "Cannot have null mother");
        Validate.notNull(father, "Cannot have null father");

        // Breeder can be null in the case of spontaneous conception
        this.mother = mother;
        this.father = father;
        this.breeder = breeder;
        this.bredWith = bredWith;

        setExperience(experience);
    }

    @NotNull
    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) entity;
    }

    /**
     * Gets the parent creating this entity.
     *
     * @return The "birth" parent
     */
    @NotNull
    public LivingEntity getMother() {
        return mother;
    }

    /**
     * Gets the other parent of the newly born entity.
     *
     * @return the other parent
     */
    @NotNull
    public LivingEntity getFather() {
        return father;
    }

    /**
     * Gets the Entity responsible for breeding. Breeder is null for spontaneous
     * conception.
     *
     * @return The Entity who initiated breeding.
     */
    @Nullable
    public LivingEntity getBreeder() {
        return breeder;
    }

    /**
     * The ItemStack that was used to initiate breeding, if present.
     *
     * @return ItemStack used to initiate breeding.
     */
    @Nullable
    public ItemStack getBredWith() {
        return bredWith;
    }

    /**
     * Get the amount of experience granted by breeding.
     *
     * @return experience amount
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Set the amount of experience granted by breeding.
     *
     * @param experience experience amount
     */
    public void setExperience(int experience) {
        Validate.isTrue(experience >= 0, "Experience cannot be negative");
        this.experience = experience;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
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
