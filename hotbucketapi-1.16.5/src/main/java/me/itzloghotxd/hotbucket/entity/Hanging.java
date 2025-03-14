package me.itzloghotxd.hotbucket.entity;

import me.itzloghotxd.hotbucket.block.BlockFace;
import me.itzloghotxd.hotbucket.material.Attachable;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a Hanging entity
 */
public interface Hanging extends Entity, Attachable {

    /**
     * Sets the direction of the hanging entity, potentially overriding rules
     * of placement. Note that if the result is not valid the object would
     * normally drop as an item.
     *
     * @param face The new direction.
     * @param force Whether to force it.
     * @return False if force was false and there was no block for it to
     *     attach to in order to face the given direction.
     */
    public boolean setFacingDirection(@NotNull BlockFace face, boolean force);
}
