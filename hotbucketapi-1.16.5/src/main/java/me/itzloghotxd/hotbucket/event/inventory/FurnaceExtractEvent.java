package me.itzloghotxd.hotbucket.event.inventory;

import me.itzloghotxd.hotbucket.Material;
import me.itzloghotxd.hotbucket.block.Block;
import me.itzloghotxd.hotbucket.entity.Player;
import me.itzloghotxd.hotbucket.event.block.BlockExpEvent;
import org.jetbrains.annotations.NotNull;

/**
 * This event is called when a player takes items out of the furnace
 */
public class FurnaceExtractEvent extends BlockExpEvent {
    private final Player player;
    private final Material itemType;
    private final int itemAmount;

    public FurnaceExtractEvent(@NotNull Player player, @NotNull Block block, @NotNull Material itemType, int itemAmount, int exp) {
        super(block, exp);
        this.player = player;
        this.itemType = itemType;
        this.itemAmount = itemAmount;
    }

    /**
     * Get the player that triggered the event
     *
     * @return the relevant player
     */
    @NotNull
    public Player getPlayer() {
        return player;
    }

    /**
     * Get the Material of the item being retrieved
     *
     * @return the material of the item
     */
    @NotNull
    public Material getItemType() {
        return itemType;
    }

    /**
     * Get the item count being retrieved
     *
     * @return the amount of the item
     */
    public int getItemAmount() {
        return itemAmount;
    }
}
