package me.itzloghotxd.hotbucket.inventory;

import me.itzloghotxd.hotbucket.Material;
import me.itzloghotxd.hotbucket.NamespacedKey;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a campfire recipe.
 */
public class CampfireRecipe extends CookingRecipe<CampfireRecipe> {

    public CampfireRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull Material source, float experience, int cookingTime) {
        super(key, result, source, experience, cookingTime);
    }

    public CampfireRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull RecipeChoice input, float experience, int cookingTime) {
        super(key, result, input, experience, cookingTime);
    }
}
