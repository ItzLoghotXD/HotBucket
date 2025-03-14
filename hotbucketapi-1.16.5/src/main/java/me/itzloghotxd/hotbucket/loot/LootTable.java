package me.itzloghotxd.hotbucket.loot;

import java.util.Collection;
import java.util.Random;
import me.itzloghotxd.hotbucket.Keyed;
import me.itzloghotxd.hotbucket.inventory.Inventory;
import me.itzloghotxd.hotbucket.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * LootTables are technical files that represent what items should be in
 * naturally generated containers, what items should be dropped when killing a
 * mob, or what items can be fished.
 *
 * See the <a href="https://minecraft.gamepedia.com/Loot_table">
 * Minecraft Wiki</a> for more information.
 */
public interface LootTable extends Keyed {

    /**
     * Returns a mutable list of loot generated by this LootTable.
     *
     * @param random the random instance to use to generate loot
     * @param context context within to populate loot
     * @return a list of ItemStacks
     */
    @NotNull
    Collection<ItemStack> populateLoot(@NotNull Random random, @NotNull LootContext context);

    /**
     * Attempt to fill an inventory with this LootTable's loot.
     *
     * @param inventory the inventory to fill
     * @param random the random instance to use to generate loot
     * @param context context within to populate loot
     */
    void fillInventory(@NotNull Inventory inventory, @NotNull Random random, @NotNull LootContext context);
}
