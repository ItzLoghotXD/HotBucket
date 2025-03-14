package me.itzloghotxd.hotbucket.inventory;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a merchant's trade.
 *
 * Trades can take one or two ingredients, and provide one result. The
 * ingredients' ItemStack amounts are respected in the trade.
 * <br>
 * A trade has a limited number of uses, after which the trade can no longer be
 * used, unless the player uses a different trade, which will cause its maximum
 * uses to increase.
 * <br>
 * A trade may or may not reward experience for being completed.
 *
 * @see me.itzloghotxd.hotbucket.event.entity.VillagerReplenishTradeEvent
 */
public class MerchantRecipe implements Recipe {

    private ItemStack result;
    private List<ItemStack> ingredients = new ArrayList<ItemStack>();
    private int uses;
    private int maxUses;
    private boolean experienceReward;
    private int villagerExperience;
    private float priceMultiplier;

    public MerchantRecipe(@NotNull ItemStack result, int maxUses) {
        this(result, 0, maxUses, false);
    }

    public MerchantRecipe(@NotNull ItemStack result, int uses, int maxUses, boolean experienceReward) {
        this(result, uses, maxUses, experienceReward, 0, 0.0F);
    }

    public MerchantRecipe(@NotNull ItemStack result, int uses, int maxUses, boolean experienceReward, int villagerExperience, float priceMultiplier) {
        this.result = result;
        this.uses = uses;
        this.maxUses = maxUses;
        this.experienceReward = experienceReward;
        this.villagerExperience = villagerExperience;
        this.priceMultiplier = priceMultiplier;
    }

    @NotNull
    @Override
    public ItemStack getResult() {
        return result;
    }

    public void addIngredient(@NotNull ItemStack item) {
        Preconditions.checkState(ingredients.size() < 2, "MerchantRecipe can only have maximum 2 ingredients");
        ingredients.add(item.clone());
    }

    public void removeIngredient(int index) {
        ingredients.remove(index);
    }

    public void setIngredients(@NotNull List<ItemStack> ingredients) {
        Preconditions.checkState(ingredients.size() <= 2, "MerchantRecipe can only have maximum 2 ingredients");
        this.ingredients = new ArrayList<ItemStack>();
        for (ItemStack item : ingredients) {
            this.ingredients.add(item.clone());
        }
    }

    @NotNull
    public List<ItemStack> getIngredients() {
        List<ItemStack> copy = new ArrayList<ItemStack>();
        for (ItemStack item : ingredients) {
            copy.add(item.clone());
        }
        return copy;
    }

    /**
     * Get the number of times this trade has been used.
     *
     * @return the number of uses
     */
    public int getUses() {
        return uses;
    }

    /**
     * Set the number of times this trade has been used.
     *
     * @param uses the number of uses
     */
    public void setUses(int uses) {
        this.uses = uses;
    }

    /**
     * Get the maximum number of uses this trade has.
     * <br>
     * The maximum uses of this trade may increase when a player trades with the
     * owning merchant.
     *
     * @return the maximum number of uses
     */
    public int getMaxUses() {
        return maxUses;
    }

    /**
     * Set the maximum number of uses this trade has.
     *
     * @param maxUses the maximum number of time this trade can be used
     */
    public void setMaxUses(int maxUses) {
        this.maxUses = maxUses;
    }

    /**
     * Whether to reward experience to the player for the trade.
     *
     * @return whether to reward experience to the player for completing this
     * trade
     */
    public boolean hasExperienceReward() {
        return experienceReward;
    }

    /**
     * Set whether to reward experience to the player for the trade.
     *
     * @param flag whether to reward experience to the player for completing
     * this trade
     */
    public void setExperienceReward(boolean flag) {
        this.experienceReward = flag;
    }

    /**
     * Gets the amount of experience the villager earns from this trade.
     *
     * @return villager experience
     */
    public int getVillagerExperience() {
        return villagerExperience;
    }

    /**
     * Sets the amount of experience the villager earns from this trade.
     *
     * @param villagerExperience new experience amount
     */
    public void setVillagerExperience(int villagerExperience) {
        this.villagerExperience = villagerExperience;
    }

    /**
     * Gets the additive price multiplier for the cost of this trade.
     *
     * @return price multiplier
     */
    public float getPriceMultiplier() {
        return priceMultiplier;
    }

    /**
     * Sets the additive price multiplier for the cost of this trade.
     *
     * @param priceMultiplier new price multiplier
     */
    public void setPriceMultiplier(float priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }
}
