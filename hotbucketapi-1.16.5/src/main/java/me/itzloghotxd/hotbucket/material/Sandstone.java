package me.itzloghotxd.hotbucket.material;

import me.itzloghotxd.hotbucket.Material;
import me.itzloghotxd.hotbucket.SandstoneType;

/**
 * Represents the different types of sandstone.
 *
 * @deprecated all usage of MaterialData is deprecated and subject to removal.
 * Use {@link me.itzloghotxd.hotbucket.block.data.BlockData}.
 */
@Deprecated
public class Sandstone extends MaterialData {
    public Sandstone() {
        super(Material.LEGACY_SANDSTONE);
    }

    public Sandstone(SandstoneType type) {
        this();
        setType(type);
    }

    public Sandstone(final Material type) {
        super(type);
    }

    /**
     * @param type the type
     * @param data the raw data value
     * @deprecated Magic value
     */
    @Deprecated
    public Sandstone(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the current type of this sandstone
     *
     * @return SandstoneType of this sandstone
     */
    public SandstoneType getType() {
        return SandstoneType.getByData(getData());
    }

    /**
     * Sets the type of this sandstone
     *
     * @param type New type of this sandstone
     */
    public void setType(SandstoneType type) {
        setData(type.getData());
    }

    @Override
    public String toString() {
        return getType() + " " + super.toString();
    }

    @Override
    public Sandstone clone() {
        return (Sandstone) super.clone();
    }
}
