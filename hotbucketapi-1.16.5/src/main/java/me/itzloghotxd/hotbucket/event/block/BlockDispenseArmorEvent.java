package me.itzloghotxd.hotbucket.event.block;

import me.itzloghotxd.hotbucket.block.Block;
import me.itzloghotxd.hotbucket.entity.LivingEntity;
import me.itzloghotxd.hotbucket.inventory.ItemStack;
import me.itzloghotxd.hotbucket.util.Vector;
import org.jetbrains.annotations.NotNull;

/**
 * Called when an equippable item is dispensed from a block and equipped on a
 * nearby entity.
 * <p>
 * If a Block Dispense Armor event is cancelled, the equipment will not be
 * equipped on the target entity.
 */
public class BlockDispenseArmorEvent extends BlockDispenseEvent {

    private final LivingEntity target;

    public BlockDispenseArmorEvent(@NotNull Block block, @NotNull ItemStack dispensed, @NotNull LivingEntity target) {
        super(block, dispensed, new Vector(0, 0, 0));
        this.target = target;
    }

    /**
     * Get the living entity on which the armor was dispensed.
     *
     * @return the target entity
     */
    @NotNull
    public LivingEntity getTargetEntity() {
        return target;
    }
}
