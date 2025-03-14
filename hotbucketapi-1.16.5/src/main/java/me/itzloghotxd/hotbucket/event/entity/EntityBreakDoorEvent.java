package me.itzloghotxd.hotbucket.event.entity;

import me.itzloghotxd.hotbucket.Material;
import me.itzloghotxd.hotbucket.block.Block;
import me.itzloghotxd.hotbucket.entity.Entity;
import me.itzloghotxd.hotbucket.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

/**
 * Called when an {@link Entity} breaks a door
 * <p>
 * Cancelling the event will cause the event to be delayed
 */
public class EntityBreakDoorEvent extends EntityChangeBlockEvent {
    public EntityBreakDoorEvent(@NotNull final LivingEntity entity, @NotNull final Block targetBlock) {
        super(entity, targetBlock, Material.AIR.createBlockData());
    }

    @NotNull
    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) entity;
    }
}
