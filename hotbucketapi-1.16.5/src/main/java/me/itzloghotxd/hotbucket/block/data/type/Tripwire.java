package me.itzloghotxd.hotbucket.block.data.type;

import me.itzloghotxd.hotbucket.block.data.Attachable;
import me.itzloghotxd.hotbucket.block.data.MultipleFacing;
import me.itzloghotxd.hotbucket.block.data.Powerable;

/**
 * 'disarmed' denotes that the tripwire was broken with shears and will not
 * subsequently produce a current when destroyed.
 */
public interface Tripwire extends Attachable, MultipleFacing, Powerable {

    /**
     * Gets the value of the 'disarmed' property.
     *
     * @return the 'disarmed' value
     */
    boolean isDisarmed();

    /**
     * Sets the value of the 'disarmed' property.
     *
     * @param disarmed the new 'disarmed' value
     */
    void setDisarmed(boolean disarmed);
}
