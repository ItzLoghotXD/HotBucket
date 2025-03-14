package me.itzloghotxd.hotbucket.block.data.type;

import me.itzloghotxd.hotbucket.block.data.Directional;

/**
 * 'extended' denotes whether the piston head is currently extended or not.
 */
public interface Piston extends Directional {

    /**
     * Gets the value of the 'extended' property.
     *
     * @return the 'extended' value
     */
    boolean isExtended();

    /**
     * Sets the value of the 'extended' property.
     *
     * @param extended the new 'extended' value
     */
    void setExtended(boolean extended);
}
