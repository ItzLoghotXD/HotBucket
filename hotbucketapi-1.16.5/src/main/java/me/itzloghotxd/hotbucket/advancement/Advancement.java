package me.itzloghotxd.hotbucket.advancement;

import java.util.Collection;
import me.itzloghotxd.hotbucket.Keyed;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an advancement that may be awarded to a player. This class is not
 * reference safe as the underlying advancement may be reloaded.
 */
public interface Advancement extends Keyed {

    /**
     * Get all the criteria present in this advancement.
     *
     * @return a unmodifiable copy of all criteria
     */
    @NotNull
    Collection<String> getCriteria();
}
