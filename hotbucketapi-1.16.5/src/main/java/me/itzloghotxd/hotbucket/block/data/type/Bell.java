package me.itzloghotxd.hotbucket.block.data.type;

import me.itzloghotxd.hotbucket.block.data.Directional;
import me.itzloghotxd.hotbucket.block.data.Powerable;
import org.jetbrains.annotations.NotNull;

/**
 * 'attachment' denotes how the bell is attached to its block.
 */
public interface Bell extends Directional, Powerable {

    /**
     * Gets the value of the 'attachment' property.
     *
     * @return the 'attachment' value
     */
    @NotNull
    Attachment getAttachment();

    /**
     * Sets the value of the 'attachment' property.
     *
     * @param attachment the new 'attachment' value
     */
    void setAttachment(@NotNull Attachment attachment);

    /**
     * What the bell is attached to.
     */
    public enum Attachment {

        /**
         * Placed on floor.
         */
        FLOOR,
        /**
         * Placed on ceiling.
         */
        CEILING,
        /**
         * Placed on one wall.
         */
        SINGLE_WALL,
        /**
         * Placed between two walls.
         */
        DOUBLE_WALL;
    }
}
