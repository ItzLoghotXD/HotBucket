package me.itzloghotxd.hotbucket.material;

import me.itzloghotxd.hotbucket.Material;
import me.itzloghotxd.hotbucket.block.BlockFace;

/**
 * Represents a chest
 *
 * @deprecated all usage of MaterialData is deprecated and subject to removal.
 * Use {@link me.itzloghotxd.hotbucket.block.data.BlockData}.
 */
@Deprecated
public class Chest extends DirectionalContainer {

    public Chest() {
        super(Material.LEGACY_CHEST);
    }

    /**
     * Instantiate a chest facing in a particular direction.
     *
     * @param direction the direction the chest's lit opens towards
     */
    public Chest(BlockFace direction) {
        this();
        setFacingDirection(direction);
    }

    public Chest(final Material type) {
        super(type);
    }

    /**
     * @param type the type
     * @param data the raw data value
     * @deprecated Magic value
     */
    @Deprecated
    public Chest(final Material type, final byte data) {
        super(type, data);
    }

    @Override
    public Chest clone() {
        return (Chest) super.clone();
    }
}
