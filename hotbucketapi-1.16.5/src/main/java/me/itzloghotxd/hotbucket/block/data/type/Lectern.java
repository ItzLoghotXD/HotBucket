package me.itzloghotxd.hotbucket.block.data.type;

import me.itzloghotxd.hotbucket.block.data.Directional;
import me.itzloghotxd.hotbucket.block.data.Powerable;

/**
 * 'has_book' is a quick flag to check whether this lectern has a book inside
 * it.
 */
public interface Lectern extends Directional, Powerable {

    /**
     * Gets the value of the 'has_book' property.
     *
     * @return the 'has_book' value
     */
    boolean hasBook();
}
