package me.itzloghotxd.hotbucket.entity;

import me.itzloghotxd.hotbucket.material.Colorable;

/**
 * Represents a Sheep.
 */
public interface Sheep extends Animals, Colorable {

    /**
     * @return Whether the sheep is sheared.
     */
    public boolean isSheared();

    /**
     * @param flag Whether to shear the sheep
     */
    public void setSheared(boolean flag);
}
