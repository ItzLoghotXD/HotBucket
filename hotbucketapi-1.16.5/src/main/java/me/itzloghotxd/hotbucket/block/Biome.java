package me.itzloghotxd.hotbucket.block;

import java.util.Locale;
import me.itzloghotxd.hotbucket.Keyed;
import me.itzloghotxd.hotbucket.NamespacedKey;
import org.jetbrains.annotations.NotNull;

/**
 * Holds all accepted Biomes in the default server
 */
public enum Biome implements Keyed {
    OCEAN,
    PLAINS,
    DESERT,
    MOUNTAINS,
    FOREST,
    TAIGA,
    SWAMP,
    RIVER,
    NETHER_WASTES,
    THE_END,
    FROZEN_OCEAN,
    FROZEN_RIVER,
    SNOWY_TUNDRA,
    SNOWY_MOUNTAINS,
    MUSHROOM_FIELDS,
    MUSHROOM_FIELD_SHORE,
    BEACH,
    DESERT_HILLS,
    WOODED_HILLS,
    TAIGA_HILLS,
    MOUNTAIN_EDGE,
    JUNGLE,
    JUNGLE_HILLS,
    JUNGLE_EDGE,
    DEEP_OCEAN,
    STONE_SHORE,
    SNOWY_BEACH,
    BIRCH_FOREST,
    BIRCH_FOREST_HILLS,
    DARK_FOREST,
    SNOWY_TAIGA,
    SNOWY_TAIGA_HILLS,
    GIANT_TREE_TAIGA,
    GIANT_TREE_TAIGA_HILLS,
    WOODED_MOUNTAINS,
    SAVANNA,
    SAVANNA_PLATEAU,
    BADLANDS,
    WOODED_BADLANDS_PLATEAU,
    BADLANDS_PLATEAU,
    SMALL_END_ISLANDS,
    END_MIDLANDS,
    END_HIGHLANDS,
    END_BARRENS,
    WARM_OCEAN,
    LUKEWARM_OCEAN,
    COLD_OCEAN,
    DEEP_WARM_OCEAN,
    DEEP_LUKEWARM_OCEAN,
    DEEP_COLD_OCEAN,
    DEEP_FROZEN_OCEAN,
    THE_VOID,
    SUNFLOWER_PLAINS,
    DESERT_LAKES,
    GRAVELLY_MOUNTAINS,
    FLOWER_FOREST,
    TAIGA_MOUNTAINS,
    SWAMP_HILLS,
    ICE_SPIKES,
    MODIFIED_JUNGLE,
    MODIFIED_JUNGLE_EDGE,
    TALL_BIRCH_FOREST,
    TALL_BIRCH_HILLS,
    DARK_FOREST_HILLS,
    SNOWY_TAIGA_MOUNTAINS,
    GIANT_SPRUCE_TAIGA,
    GIANT_SPRUCE_TAIGA_HILLS,
    MODIFIED_GRAVELLY_MOUNTAINS,
    SHATTERED_SAVANNA,
    SHATTERED_SAVANNA_PLATEAU,
    ERODED_BADLANDS,
    MODIFIED_WOODED_BADLANDS_PLATEAU,
    MODIFIED_BADLANDS_PLATEAU,
    BAMBOO_JUNGLE,
    BAMBOO_JUNGLE_HILLS,
    SOUL_SAND_VALLEY,
    CRIMSON_FOREST,
    WARPED_FOREST,
    BASALT_DELTAS,
    /**
     * Represents a custom Biome
     */
    CUSTOM;

    private final NamespacedKey key;

    private Biome() {
        this.key = NamespacedKey.minecraft(name().toLowerCase(Locale.ROOT));
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return key;
    }
}
