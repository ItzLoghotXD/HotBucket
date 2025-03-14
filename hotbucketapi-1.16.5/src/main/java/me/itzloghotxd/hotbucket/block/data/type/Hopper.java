package me.itzloghotxd.hotbucket.block.data.type;

import me.itzloghotxd.hotbucket.block.data.Directional;
import me.itzloghotxd.hotbucket.block.data.Powerable;

/**
 * Similar to {@link Powerable}, 'enabled' indicates whether or not the hopper
 * is currently activated.
 * <br>
 * Unlike most other blocks, a hopper is only enabled when it is <b>not</b>
 * receiving any power.
 */
public interface Hopper extends Directional {

    /**
     * Gets the value of the 'enabled' property.
     *
     * @return the 'enabled' value
     */
    boolean isEnabled();

    /**
     * Sets the value of the 'enabled' property.
     *
     * @param enabled the new 'enabled' value
     */
    void setEnabled(boolean enabled);
}
