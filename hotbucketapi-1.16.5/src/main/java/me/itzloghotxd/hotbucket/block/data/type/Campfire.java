package me.itzloghotxd.hotbucket.block.data.type;

import me.itzloghotxd.hotbucket.block.data.Directional;
import me.itzloghotxd.hotbucket.block.data.Lightable;
import me.itzloghotxd.hotbucket.block.data.Waterlogged;

/**
 * 'signal_fire' denotes whether the fire is extra smokey due to having a hay
 * bale placed beneath it.
 */
public interface Campfire extends Directional, Lightable, Waterlogged {

    /**
     * Gets the value of the 'signal_fire' property.
     *
     * @return the 'signal_fire' value
     */
    boolean isSignalFire();

    /**
     * Sets the value of the 'signal_fire' property.
     *
     * @param signalFire the new 'signal_fire' value
     */
    void setSignalFire(boolean signalFire);
}
