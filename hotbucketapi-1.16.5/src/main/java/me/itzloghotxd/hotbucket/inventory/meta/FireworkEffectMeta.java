package me.itzloghotxd.hotbucket.inventory.meta;

import me.itzloghotxd.hotbucket.FireworkEffect;
import me.itzloghotxd.hotbucket.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a meta that can store a single FireworkEffect. An example
 * includes {@link Material#FIREWORK_STAR}.
 */
public interface FireworkEffectMeta extends ItemMeta {

    /**
     * Sets the firework effect for this meta.
     *
     * @param effect the effect to set, or null to indicate none.
     */
    void setEffect(@Nullable FireworkEffect effect);

    /**
     * Checks if this meta has an effect.
     *
     * @return true if this meta has an effect, false otherwise
     */
    boolean hasEffect();

    /**
     * Gets the firework effect for this meta.
     *
     * @return the current effect, or null if none
     */
    @Nullable
    FireworkEffect getEffect();

    @Override
    @NotNull
    FireworkEffectMeta clone();
}
