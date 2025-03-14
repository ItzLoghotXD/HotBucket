package me.itzloghotxd.hotbucket;

import com.google.common.collect.ImmutableList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.logging.Logger;
import me.itzloghotxd.hotbucket.Warning.WarningState;
import me.itzloghotxd.hotbucket.advancement.Advancement;
import me.itzloghotxd.hotbucket.block.data.BlockData;
import me.itzloghotxd.hotbucket.boss.BarColor;
import me.itzloghotxd.hotbucket.boss.BarFlag;
import me.itzloghotxd.hotbucket.boss.BarStyle;
import me.itzloghotxd.hotbucket.boss.BossBar;
import me.itzloghotxd.hotbucket.boss.KeyedBossBar;
import me.itzloghotxd.hotbucket.command.CommandException;
import me.itzloghotxd.hotbucket.command.CommandSender;
import me.itzloghotxd.hotbucket.command.ConsoleCommandSender;
import me.itzloghotxd.hotbucket.command.PluginCommand;
import me.itzloghotxd.hotbucket.entity.Entity;
import me.itzloghotxd.hotbucket.entity.Player;
import me.itzloghotxd.hotbucket.event.inventory.InventoryType;
import me.itzloghotxd.hotbucket.event.server.ServerListPingEvent;
import me.itzloghotxd.hotbucket.generator.ChunkGenerator;
import me.itzloghotxd.hotbucket.help.HelpMap;
import me.itzloghotxd.hotbucket.inventory.Inventory;
import me.itzloghotxd.hotbucket.inventory.InventoryHolder;
import me.itzloghotxd.hotbucket.inventory.ItemFactory;
import me.itzloghotxd.hotbucket.inventory.ItemStack;
import me.itzloghotxd.hotbucket.inventory.Merchant;
import me.itzloghotxd.hotbucket.inventory.Recipe;
import me.itzloghotxd.hotbucket.inventory.meta.ItemMeta;
import me.itzloghotxd.hotbucket.loot.LootTable;
import me.itzloghotxd.hotbucket.map.MapView;
import me.itzloghotxd.hotbucket.permissions.Permissible;
import me.itzloghotxd.hotbucket.plugin.PluginManager;
import me.itzloghotxd.hotbucket.plugin.ServicesManager;
import me.itzloghotxd.hotbucket.plugin.messaging.Messenger;
import me.itzloghotxd.hotbucket.plugin.messaging.PluginMessageRecipient;
import me.itzloghotxd.hotbucket.scheduler.BukkitScheduler;
import me.itzloghotxd.hotbucket.scoreboard.ScoreboardManager;
import me.itzloghotxd.hotbucket.util.CachedServerIcon;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a server implementation.
 */
public interface Server extends PluginMessageRecipient {

    /**
     * Used for all administrative messages, such as an operator using a
     * command.
     * <p>
     * For use in {@link #broadcast(java.lang.String, java.lang.String)}.
     */
    public static final String BROADCAST_CHANNEL_ADMINISTRATIVE = "bukkit.broadcast.admin";

    /**
     * Used for all announcement messages, such as informing users that a
     * player has joined.
     * <p>
     * For use in {@link #broadcast(java.lang.String, java.lang.String)}.
     */
    public static final String BROADCAST_CHANNEL_USERS = "bukkit.broadcast.user";

    /**
     * Gets the name of this server implementation.
     *
     * @return name of this server implementation
     */
    @NotNull
    public String getName();

    /**
     * Gets the version string of this server implementation.
     *
     * @return version of this server implementation
     */
    @NotNull
    public String getVersion();

    /**
     * Gets the Bukkit version that this server is running.
     *
     * @return version of Bukkit
     */
    @NotNull
    public String getBukkitVersion();

    /**
     * Gets a view of all currently logged in players. This {@linkplain
     * Collections#unmodifiableCollection(Collection) view} is a reused
     * object, making some operations like {@link Collection#size()}
     * zero-allocation.
     * <p>
     * The collection is a view backed by the internal representation, such
     * that, changes to the internal state of the server will be reflected
     * immediately. However, the reuse of the returned collection (identity)
     * is not strictly guaranteed for future or all implementations. Casting
     * the collection, or relying on interface implementations (like {@link
     * Serializable} or {@link List}), is deprecated.
     * <p>
     * Iteration behavior is undefined outside of self-contained main-thread
     * uses. Normal and immediate iterator use without consequences that
     * affect the collection are fully supported. The effects following
     * (non-exhaustive) {@link Entity#teleport(Location) teleportation},
     * {@link Player#setHealth(double) death}, and {@link Player#kickPlayer(
     * String) kicking} are undefined. Any use of this collection from
     * asynchronous threads is unsafe.
     * <p>
     * For safe consequential iteration or mimicking the old array behavior,
     * using {@link Collection#toArray(Object[])} is recommended. For making
     * snapshots, {@link ImmutableList#copyOf(Collection)} is recommended.
     *
     * @return a view of currently online players.
     */
    @NotNull
    public Collection<? extends Player> getOnlinePlayers();

    /**
     * Get the maximum amount of players which can login to this server.
     *
     * @return the amount of players this server allows
     */
    public int getMaxPlayers();

    /**
     * Get the game port that the server runs on.
     *
     * @return the port number of this server
     */
    public int getPort();

    /**
     * Get the view distance from this server.
     *
     * @return the view distance from this server.
     */
    public int getViewDistance();

    /**
     * Get the IP that this server is bound to, or empty string if not
     * specified.
     *
     * @return the IP string that this server is bound to, otherwise empty
     *     string
     */
    @NotNull
    public String getIp();

    /**
     * Get world type (level-type setting) for default world.
     *
     * @return the value of level-type (e.g. DEFAULT, FLAT, DEFAULT_1_1)
     */
    @NotNull
    public String getWorldType();

    /**
     * Get generate-structures setting.
     *
     * @return true if structure generation is enabled, false otherwise
     */
    public boolean getGenerateStructures();

    /**
     * Get max world size.
     *
     * @return the maximum world size as specified for the server
     */
    public int getMaxWorldSize();

    /**
     * Gets whether this server allows the End or not.
     *
     * @return whether this server allows the End or not
     */
    public boolean getAllowEnd();

    /**
     * Gets whether this server allows the Nether or not.
     *
     * @return whether this server allows the Nether or not
     */
    public boolean getAllowNether();

    /**
     * Gets whether this server has a whitelist or not.
     *
     * @return whether this server has a whitelist or not
     */
    public boolean hasWhitelist();

    /**
     * Sets if the server is whitelisted.
     *
     * @param value true for whitelist on, false for off
     */
    public void setWhitelist(boolean value);

    /**
     * Gets a list of whitelisted players.
     *
     * @return a set containing all whitelisted players
     */
    @NotNull
    public Set<OfflinePlayer> getWhitelistedPlayers();

    /**
     * Reloads the whitelist from disk.
     */
    public void reloadWhitelist();

    /**
     * Broadcast a message to all players.
     * <p>
     * This is the same as calling {@link #broadcast(java.lang.String,
     * java.lang.String)} to {@link #BROADCAST_CHANNEL_USERS}
     *
     * @param message the message
     * @return the number of players
     */
    public int broadcastMessage(@NotNull String message);

    /**
     * Gets the name of the update folder. The update folder is used to safely
     * update plugins at the right moment on a plugin load.
     * <p>
     * The update folder name is relative to the plugins folder.
     *
     * @return the name of the update folder
     */
    @NotNull
    public String getUpdateFolder();

    /**
     * Gets the update folder. The update folder is used to safely update
     * plugins at the right moment on a plugin load.
     *
     * @return the update folder
     */
    @NotNull
    public File getUpdateFolderFile();

    /**
     * Gets the value of the connection throttle setting.
     *
     * @return the value of the connection throttle setting
     */
    public long getConnectionThrottle();

    /**
     * Gets default ticks per animal spawns value.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn monsters
     *     every tick.
     * <li>A value of 400 will mean the server will attempt to spawn monsters
     *     every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b> If set to 0, animal spawning will be disabled. We
     * recommend using spawn-animals to control this instead.
     * <p>
     * Minecraft default: 400.
     *
     * @return the default ticks per animal spawns value
     */
    public int getTicksPerAnimalSpawns();

    /**
     * Gets the default ticks per monster spawns value.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn monsters
     *     every tick.
     * <li>A value of 400 will mean the server will attempt to spawn monsters
     *     every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b> If set to 0, monsters spawning will be disabled. We
     * recommend using spawn-monsters to control this instead.
     * <p>
     * Minecraft default: 1.
     *
     * @return the default ticks per monsters spawn value
     */
    public int getTicksPerMonsterSpawns();

    /**
     * Gets the default ticks per water mob spawns value.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn water mobs
     *     every tick.
     * <li>A value of 400 will mean the server will attempt to spawn water mobs
     *     every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b> If set to 0, water mobs spawning will be disabled.
     * <p>
     * Minecraft default: 1.
     *
     * @return the default ticks per water mobs spawn value
     */
    public int getTicksPerWaterSpawns();

    /**
     * Gets the default ticks per water ambient mob spawns value.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn water ambient mobs
     *     every tick.
     * <li>A value of 400 will mean the server will attempt to spawn water ambient mobs
     *     every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b> If set to 0, ambient mobs spawning will be disabled.
     * <p>
     * Minecraft default: 1.
     *
     * @return the default ticks per water ambient mobs spawn value
     */
    public int getTicksPerWaterAmbientSpawns();

    /**
     * Gets the default ticks per ambient mob spawns value.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn ambient mobs
     *     every tick.
     * <li>A value of 400 will mean the server will attempt to spawn ambient mobs
     *     every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b> If set to 0, ambient mobs spawning will be disabled.
     * <p>
     * Minecraft default: 1.
     *
     * @return the default ticks per ambient mobs spawn value
     */
    public int getTicksPerAmbientSpawns();

    /**
     * Gets a player object by the given username.
     * <p>
     * This method may not return objects for offline players.
     *
     * @param name the name to look up
     * @return a player if one was found, null otherwise
     */
    @Nullable
    public Player getPlayer(@NotNull String name);

    /**
     * Gets the player with the exact given name, case insensitive.
     *
     * @param name Exact name of the player to retrieve
     * @return a player object if one was found, null otherwise
     */
    @Nullable
    public Player getPlayerExact(@NotNull String name);

    /**
     * Attempts to match any players with the given name, and returns a list
     * of all possibly matches.
     * <p>
     * This list is not sorted in any particular order. If an exact match is
     * found, the returned list will only contain a single result.
     *
     * @param name the (partial) name to match
     * @return list of all possible players
     */
    @NotNull
    public List<Player> matchPlayer(@NotNull String name);

    /**
     * Gets the player with the given UUID.
     *
     * @param id UUID of the player to retrieve
     * @return a player object if one was found, null otherwise
     */
    @Nullable
    public Player getPlayer(@NotNull UUID id);

    /**
     * Gets the plugin manager for interfacing with plugins.
     *
     * @return a plugin manager for this Server instance
     */
    @NotNull
    public PluginManager getPluginManager();

    /**
     * Gets the scheduler for managing scheduled events.
     *
     * @return a scheduling service for this server
     */
    @NotNull
    public BukkitScheduler getScheduler();

    /**
     * Gets a services manager.
     *
     * @return s services manager
     */
    @NotNull
    public ServicesManager getServicesManager();

    /**
     * Gets a list of all worlds on this server.
     *
     * @return a list of worlds
     */
    @NotNull
    public List<World> getWorlds();

    /**
     * Creates or loads a world with the given name using the specified
     * options.
     * <p>
     * If the world is already loaded, it will just return the equivalent of
     * getWorld(creator.name()).
     *
     * @param creator the options to use when creating the world
     * @return newly created or loaded world
     */
    @Nullable
    public World createWorld(@NotNull WorldCreator creator);

    /**
     * Unloads a world with the given name.
     *
     * @param name Name of the world to unload
     * @param save whether to save the chunks before unloading
     * @return true if successful, false otherwise
     */
    public boolean unloadWorld(@NotNull String name, boolean save);

    /**
     * Unloads the given world.
     *
     * @param world the world to unload
     * @param save whether to save the chunks before unloading
     * @return true if successful, false otherwise
     */
    public boolean unloadWorld(@NotNull World world, boolean save);

    /**
     * Gets the world with the given name.
     *
     * @param name the name of the world to retrieve
     * @return a world with the given name, or null if none exists
     */
    @Nullable
    public World getWorld(@NotNull String name);

    /**
     * Gets the world from the given Unique ID.
     *
     * @param uid a unique-id of the world to retrieve
     * @return a world with the given Unique ID, or null if none exists
     */
    @Nullable
    public World getWorld(@NotNull UUID uid);

    /**
     * Gets the map from the given item ID.
     *
     * @param id the id of the map to get
     * @return a map view if it exists, or null otherwise
     * @deprecated Magic value
     */
    @Deprecated
    @Nullable
    public MapView getMap(int id);

    /**
     * Create a new map with an automatically assigned ID.
     *
     * @param world the world the map will belong to
     * @return a newly created map view
     */
    @NotNull
    public MapView createMap(@NotNull World world);

    /**
     * Create a new explorer map targeting the closest nearby structure of a
     * given {@link StructureType}.
     * <br>
     * This method uses implementation default values for radius and
     * findUnexplored (usually 100, true).
     *
     * @param world the world the map will belong to
     * @param location the origin location to find the nearest structure
     * @param structureType the type of structure to find
     * @return a newly created item stack
     *
     * @see World#locateNearestStructure(me.itzloghotxd.hotbucket.Location,
     *      me.itzloghotxd.hotbucket.StructureType, int, boolean)
     */
    @NotNull
    public ItemStack createExplorerMap(@NotNull World world, @NotNull Location location, @NotNull StructureType structureType);

    /**
     * Create a new explorer map targeting the closest nearby structure of a
     * given {@link StructureType}.
     * <br>
     * This method uses implementation default values for radius and
     * findUnexplored (usually 100, true).
     *
     * @param world the world the map will belong to
     * @param location the origin location to find the nearest structure
     * @param structureType the type of structure to find
     * @param radius radius to search, see World#locateNearestStructure for more
     *               information
     * @param findUnexplored whether to find unexplored structures
     * @return the newly created item stack
     *
     * @see World#locateNearestStructure(me.itzloghotxd.hotbucket.Location,
     *      me.itzloghotxd.hotbucket.StructureType, int, boolean)
     */
    @NotNull
    public ItemStack createExplorerMap(@NotNull World world, @NotNull Location location, @NotNull StructureType structureType, int radius, boolean findUnexplored);

    /**
     * Reloads the server, refreshing settings and plugin information.
     */
    public void reload();

    /**
     * Reload only the Minecraft data for the server. This includes custom
     * advancements and loot tables.
     */
    public void reloadData();

    /**
     * Returns the primary logger associated with this server instance.
     *
     * @return Logger associated with this server
     */
    @NotNull
    public Logger getLogger();

    /**
     * Gets a {@link PluginCommand} with the given name or alias.
     *
     * @param name the name of the command to retrieve
     * @return a plugin command if found, null otherwise
     */
    @Nullable
    public PluginCommand getPluginCommand(@NotNull String name);

    /**
     * Writes loaded players to disk.
     */
    public void savePlayers();

    /**
     * Dispatches a command on this server, and executes it if found.
     *
     * @param sender the apparent sender of the command
     * @param commandLine the command + arguments. Example: <code>test abc
     *     123</code>
     * @return returns false if no target is found
     * @throws CommandException thrown when the executor for the given command
     *     fails with an unhandled exception
     */
    public boolean dispatchCommand(@NotNull CommandSender sender, @NotNull String commandLine) throws CommandException;

    /**
     * Adds a recipe to the crafting manager.
     *
     * @param recipe the recipe to add
     * @return true if the recipe was added, false if it wasn't for some
     *     reason
     */
    @Contract("null -> false")
    public boolean addRecipe(@Nullable Recipe recipe);

    /**
     * Get a list of all recipes for a given item. The stack size is ignored
     * in comparisons. If the durability is -1, it will match any data value.
     *
     * @param result the item to match against recipe results
     * @return a list of recipes with the given result
     */
    @NotNull
    public List<Recipe> getRecipesFor(@NotNull ItemStack result);

    /**
     * Get the {@link Recipe} for the given key.
     *
     * @param recipeKey the key of the recipe to return
     * @return the recipe for the given key or null.
     */
    @Nullable
    public Recipe getRecipe(@NotNull NamespacedKey recipeKey);

    /**
     * Get an iterator through the list of crafting recipes.
     *
     * @return an iterator
     */
    @NotNull
    public Iterator<Recipe> recipeIterator();

    /**
     * Clears the list of crafting recipes.
     */
    public void clearRecipes();

    /**
     * Resets the list of crafting recipes to the default.
     */
    public void resetRecipes();

    /**
     * Remove a recipe from the server.
     *
     * <b>Note that removing a recipe may cause permanent loss of data
     * associated with that recipe (eg whether it has been discovered by
     * players).</b>
     *
     * @param key NamespacedKey of recipe to remove.
     * @return True if recipe was removed
     */
    public boolean removeRecipe(@NotNull NamespacedKey key);

    /**
     * Gets a list of command aliases defined in the server properties.
     *
     * @return a map of aliases to command names
     */
    @NotNull
    public Map<String, String[]> getCommandAliases();

    /**
     * Gets the radius, in blocks, around each worlds spawn point to protect.
     *
     * @return spawn radius, or 0 if none
     */
    public int getSpawnRadius();

    /**
     * Sets the radius, in blocks, around each worlds spawn point to protect.
     *
     * @param value new spawn radius, or 0 if none
     */
    public void setSpawnRadius(int value);

    /**
     * Gets whether the Server is in online mode or not.
     *
     * @return true if the server authenticates clients, false otherwise
     */
    public boolean getOnlineMode();

    /**
     * Gets whether this server allows flying or not.
     *
     * @return true if the server allows flight, false otherwise
     */
    public boolean getAllowFlight();

    /**
     * Gets whether the server is in hardcore mode or not.
     *
     * @return true if the server mode is hardcore, false otherwise
     */
    public boolean isHardcore();

    /**
     * Shutdowns the server, stopping everything.
     */
    public void shutdown();

    /**
     * Broadcasts the specified message to every user with the given
     * permission name.
     *
     * @param message message to broadcast
     * @param permission the required permission {@link Permissible
     *     permissibles} must have to receive the broadcast
     * @return number of message recipients
     */
    public int broadcast(@NotNull String message, @NotNull String permission);

    /**
     * Gets the player by the given name, regardless if they are offline or
     * online.
     * <p>
     * This method may involve a blocking web request to get the UUID for the
     * given name.
     * <p>
     * This will return an object even if the player does not exist. To this
     * method, all players will exist.
     *
     * @deprecated Persistent storage of users should be by UUID as names are no longer
     *             unique past a single session.
     * @param name the name the player to retrieve
     * @return an offline player
     * @see #getOfflinePlayer(java.util.UUID)
     */
    @Deprecated
    @NotNull
    public OfflinePlayer getOfflinePlayer(@NotNull String name);

    /**
     * Gets the player by the given UUID, regardless if they are offline or
     * online.
     * <p>
     * This will return an object even if the player does not exist. To this
     * method, all players will exist.
     *
     * @param id the UUID of the player to retrieve
     * @return an offline player
     */
    @NotNull
    public OfflinePlayer getOfflinePlayer(@NotNull UUID id);

    /**
     * Gets a set containing all current IPs that are banned.
     *
     * @return a set containing banned IP addresses
     */
    @NotNull
    public Set<String> getIPBans();

    /**
     * Bans the specified address from the server.
     *
     * @param address the IP address to ban
     */
    public void banIP(@NotNull String address);

    /**
     * Unbans the specified address from the server.
     *
     * @param address the IP address to unban
     */
    public void unbanIP(@NotNull String address);

    /**
     * Gets a set containing all banned players.
     *
     * @return a set containing banned players
     */
    @NotNull
    public Set<OfflinePlayer> getBannedPlayers();

    /**
     * Gets a ban list for the supplied type.
     * <p>
     * Bans by name are no longer supported and this method will return
     * null when trying to request them. The replacement is bans by UUID.
     *
     * @param type the type of list to fetch, cannot be null
     * @return a ban list of the specified type
     */
    @NotNull
    public BanList getBanList(@NotNull BanList.Type type);

    /**
     * Gets a set containing all player operators.
     *
     * @return a set containing player operators
     */
    @NotNull
    public Set<OfflinePlayer> getOperators();

    /**
     * Gets the default {@link GameMode} for new players.
     *
     * @return the default game mode
     */
    @NotNull
    public GameMode getDefaultGameMode();

    /**
     * Sets the default {@link GameMode} for new players.
     *
     * @param mode the new game mode
     */
    public void setDefaultGameMode(@NotNull GameMode mode);

    /**
     * Gets a {@link ConsoleCommandSender} that may be used as an input source
     * for this server.
     *
     * @return a console command sender
     */
    @NotNull
    public ConsoleCommandSender getConsoleSender();

    /**
     * Gets the folder that contains all of the various {@link World}s.
     *
     * @return folder that contains all worlds
     */
    @NotNull
    public File getWorldContainer();

    /**
     * Gets every player that has ever played on this server.
     *
     * @return an array containing all previous players
     */
    @NotNull
    public OfflinePlayer[] getOfflinePlayers();

    /**
     * Gets the {@link Messenger} responsible for this server.
     *
     * @return messenger responsible for this server
     */
    @NotNull
    public Messenger getMessenger();

    /**
     * Gets the {@link HelpMap} providing help topics for this server.
     *
     * @return a help map for this server
     */
    @NotNull
    public HelpMap getHelpMap();

    /**
     * Creates an empty inventory with the specified type. If the type
     * is {@link InventoryType#CHEST}, the new inventory has a size of 27;
     * otherwise the new inventory has the normal size for its type.
     * <br>
     * {@link InventoryType#WORKBENCH} will not process crafting recipes if
     * created with this method. Use
     * {@link Player#openWorkbench(Location, boolean)} instead.
     * <br>
     * {@link InventoryType#ENCHANTING} will not process {@link ItemStack}s
     * for possible enchanting results. Use
     * {@link Player#openEnchanting(Location, boolean)} instead.
     *
     * @param owner the holder of the inventory, or null to indicate no holder
     * @param type the type of inventory to create
     * @return a new inventory
     * @throws IllegalArgumentException if the {@link InventoryType} cannot be
     * viewed.
     *
     * @see InventoryType#isCreatable()
     */
    @NotNull
    Inventory createInventory(@Nullable InventoryHolder owner, @NotNull InventoryType type);

    /**
     * Creates an empty inventory with the specified type and title. If the type
     * is {@link InventoryType#CHEST}, the new inventory has a size of 27;
     * otherwise the new inventory has the normal size for its type.<br>
     * It should be noted that some inventory types do not support titles and
     * may not render with said titles on the Minecraft client.
     * <br>
     * {@link InventoryType#WORKBENCH} will not process crafting recipes if
     * created with this method. Use
     * {@link Player#openWorkbench(Location, boolean)} instead.
     * <br>
     * {@link InventoryType#ENCHANTING} will not process {@link ItemStack}s
     * for possible enchanting results. Use
     * {@link Player#openEnchanting(Location, boolean)} instead.
     *
     * @param owner The holder of the inventory; can be null if there's no holder.
     * @param type The type of inventory to create.
     * @param title The title of the inventory, to be displayed when it is viewed.
     * @return The new inventory.
     * @throws IllegalArgumentException if the {@link InventoryType} cannot be
     * viewed.
     *
     * @see InventoryType#isCreatable()
     */
    @NotNull
    Inventory createInventory(@Nullable InventoryHolder owner, @NotNull InventoryType type, @NotNull String title);

    /**
     * Creates an empty inventory of type {@link InventoryType#CHEST} with the
     * specified size.
     *
     * @param owner the holder of the inventory, or null to indicate no holder
     * @param size a multiple of 9 as the size of inventory to create
     * @return a new inventory
     * @throws IllegalArgumentException if the size is not a multiple of 9
     */
    @NotNull
    Inventory createInventory(@Nullable InventoryHolder owner, int size) throws IllegalArgumentException;

    /**
     * Creates an empty inventory of type {@link InventoryType#CHEST} with the
     * specified size and title.
     *
     * @param owner the holder of the inventory, or null to indicate no holder
     * @param size a multiple of 9 as the size of inventory to create
     * @param title the title of the inventory, displayed when inventory is
     *     viewed
     * @return a new inventory
     * @throws IllegalArgumentException if the size is not a multiple of 9
     */
    @NotNull
    Inventory createInventory(@Nullable InventoryHolder owner, int size, @NotNull String title) throws IllegalArgumentException;

    /**
     * Creates an empty merchant.
     *
     * @param title the title of the corresponding merchant inventory, displayed
     * when the merchant inventory is viewed
     * @return a new merchant
     */
    @NotNull
    Merchant createMerchant(@Nullable String title);

    /**
     * Gets user-specified limit for number of monsters that can spawn in a
     * chunk.
     *
     * @return the monster spawn limit
     */
    int getMonsterSpawnLimit();

    /**
     * Gets user-specified limit for number of animals that can spawn in a
     * chunk.
     *
     * @return the animal spawn limit
     */
    int getAnimalSpawnLimit();

    /**
     * Gets user-specified limit for number of water animals that can spawn in
     * a chunk.
     *
     * @return the water animal spawn limit
     */
    int getWaterAnimalSpawnLimit();

    /**
     * Gets user-specified limit for number of water ambient mobs that can spawn
     * in a chunk.
     *
     * @return the water ambient spawn limit
     */
    int getWaterAmbientSpawnLimit();

    /**
     * Gets user-specified limit for number of ambient mobs that can spawn in
     * a chunk.
     *
     * @return the ambient spawn limit
     */
    int getAmbientSpawnLimit();

    /**
     * Checks the current thread against the expected primary thread for the
     * server.
     * <p>
     * <b>Note:</b> this method should not be used to indicate the current
     * synchronized state of the runtime. A current thread matching the main
     * thread indicates that it is synchronized, but a mismatch <b>does not
     * preclude</b> the same assumption.
     *
     * @return true if the current thread matches the expected primary thread,
     *     false otherwise
     */
    boolean isPrimaryThread();

    /**
     * Gets the message that is displayed on the server list.
     *
     * @return the servers MOTD
     */
    @NotNull
    String getMotd();

    /**
     * Gets the default message that is displayed when the server is stopped.
     *
     * @return the shutdown message
     */
    @Nullable
    String getShutdownMessage();

    /**
     * Gets the current warning state for the server.
     *
     * @return the configured warning state
     */
    @NotNull
    public WarningState getWarningState();

    /**
     * Gets the instance of the item factory (for {@link ItemMeta}).
     *
     * @return the item factory
     * @see ItemFactory
     */
    @NotNull
    ItemFactory getItemFactory();

    /**
     * Gets the instance of the scoreboard manager.
     * <p>
     * This will only exist after the first world has loaded.
     *
     * @return the scoreboard manager or null if no worlds are loaded.
     */
    @Nullable
    ScoreboardManager getScoreboardManager();

    /**
     * Gets an instance of the server's default server-icon.
     *
     * @return the default server-icon; null values may be used by the
     *     implementation to indicate no defined icon, but this behavior is
     *     not guaranteed
     */
    @Nullable
    CachedServerIcon getServerIcon();

    /**
     * Loads an image from a file, and returns a cached image for the specific
     * server-icon.
     * <p>
     * Size and type are implementation defined. An incompatible file is
     * guaranteed to throw an implementation-defined {@link Exception}.
     *
     * @param file the file to load the from
     * @throws IllegalArgumentException if image is null
     * @throws Exception if the image does not meet current server server-icon
     *     specifications
     * @return a cached server-icon that can be used for a {@link
     *     ServerListPingEvent#setServerIcon(CachedServerIcon)}
     */
    @NotNull
    CachedServerIcon loadServerIcon(@NotNull File file) throws IllegalArgumentException, Exception;

    /**
     * Creates a cached server-icon for the specific image.
     * <p>
     * Size and type are implementation defined. An incompatible file is
     * guaranteed to throw an implementation-defined {@link Exception}.
     *
     * @param image the image to use
     * @throws IllegalArgumentException if image is null
     * @throws Exception if the image does not meet current server
     *     server-icon specifications
     * @return a cached server-icon that can be used for a {@link
     *     ServerListPingEvent#setServerIcon(CachedServerIcon)}
     */
    @NotNull
    CachedServerIcon loadServerIcon(@NotNull BufferedImage image) throws IllegalArgumentException, Exception;

    /**
     * Set the idle kick timeout. Any players idle for the specified amount of
     * time will be automatically kicked.
     * <p>
     * A value of 0 will disable the idle kick timeout.
     *
     * @param threshold the idle timeout in minutes
     */
    public void setIdleTimeout(int threshold);

    /**
     * Gets the idle kick timeout.
     *
     * @return the idle timeout in minutes
     */
    public int getIdleTimeout();

    /**
     * Create a ChunkData for use in a generator.
     *
     * See {@link ChunkGenerator#generateChunkData(me.itzloghotxd.hotbucket.World, java.util.Random, int, int, me.itzloghotxd.hotbucket.generator.ChunkGenerator.BiomeGrid)}
     *
     * @param world the world to create the ChunkData for
     * @return a new ChunkData for the world
     *
     */
    @NotNull
    public ChunkGenerator.ChunkData createChunkData(@NotNull World world);

    /**
     * Creates a boss bar instance to display to players. The progress
     * defaults to 1.0
     *
     * @param title the title of the boss bar
     * @param color the color of the boss bar
     * @param style the style of the boss bar
     * @param flags an optional list of flags to set on the boss bar
     * @return the created boss bar
     */
    @NotNull
    BossBar createBossBar(@Nullable String title, @NotNull BarColor color, @NotNull BarStyle style, @NotNull BarFlag... flags);

    /**
     * Creates a boss bar instance to display to players. The progress defaults
     * to 1.0.
     * <br>
     * This instance is added to the persistent storage of the server and will
     * be editable by commands and restored after restart.
     *
     * @param key the key of the boss bar that is used to access the boss bar
     * @param title the title of the boss bar
     * @param color the color of the boss bar
     * @param style the style of the boss bar
     * @param flags an optional list of flags to set on the boss bar
     * @return the created boss bar
     */
    @NotNull
    KeyedBossBar createBossBar(@NotNull NamespacedKey key, @Nullable String title, @NotNull BarColor color, @NotNull BarStyle style, @NotNull BarFlag... flags);

    /**
     * Gets an unmodifiable iterator through all persistent bossbars.
     * <ul>
     *   <li><b>not</b> bound to a {@link me.itzloghotxd.hotbucket.entity.Boss}</li>
     *   <li>
     *     <b>not</b> created using
     *     {@link #createBossBar(String, BarColor, BarStyle, BarFlag...)}
     *   </li>
     * </ul>
     *
     * e.g. bossbars created using the bossbar command
     *
     * @return a bossbar iterator
     */
    @NotNull
    Iterator<KeyedBossBar> getBossBars();

    /**
     * Gets the {@link KeyedBossBar} specified by this key.
     * <ul>
     *   <li><b>not</b> bound to a {@link me.itzloghotxd.hotbucket.entity.Boss}</li>
     *   <li>
     *     <b>not</b> created using
     *     {@link #createBossBar(String, BarColor, BarStyle, BarFlag...)}
     *   </li>
     * </ul>
     *
     * e.g. bossbars created using the bossbar command
     *
     * @param key unique bossbar key
     * @return bossbar or null if not exists
     */
    @Nullable
    KeyedBossBar getBossBar(@NotNull NamespacedKey key);

    /**
     * Removes a {@link KeyedBossBar} specified by this key.
     * <ul>
     *   <li><b>not</b> bound to a {@link me.itzloghotxd.hotbucket.entity.Boss}</li>
     *   <li>
     *     <b>not</b> created using
     *     {@link #createBossBar(String, BarColor, BarStyle, BarFlag...)}
     *   </li>
     * </ul>
     *
     * e.g. bossbars created using the bossbar command
     *
     * @param key unique bossbar key
     * @return true if removal succeeded or false
     */
    boolean removeBossBar(@NotNull NamespacedKey key);

    /**
     * Gets an entity on the server by its UUID
     *
     * @param uuid the UUID of the entity
     * @return the entity with the given UUID, or null if it isn't found
     */
    @Nullable
    Entity getEntity(@NotNull UUID uuid);

    /**
     * Get the advancement specified by this key.
     *
     * @param key unique advancement key
     * @return advancement or null if not exists
     */
    @Nullable
    Advancement getAdvancement(@NotNull NamespacedKey key);

    /**
     * Get an iterator through all advancements. Advancements cannot be removed
     * from this iterator,
     *
     * @return an advancement iterator
     */
    @NotNull
    Iterator<Advancement> advancementIterator();

    /**
     * Creates a new {@link BlockData} instance for the specified Material, with
     * all properties initialized to unspecified defaults.
     *
     * @param material the material
     * @return new data instance
     */
    @NotNull
    BlockData createBlockData(@NotNull Material material);

    /**
     * Creates a new {@link BlockData} instance for the specified Material, with
     * all properties initialized to unspecified defaults.
     *
     * @param material the material
     * @param consumer consumer to run on new instance before returning
     * @return new data instance
     */
    @NotNull
    public BlockData createBlockData(@NotNull Material material, @Nullable Consumer<BlockData> consumer);

    /**
     * Creates a new {@link BlockData} instance with material and properties
     * parsed from provided data.
     *
     * @param data data string
     * @return new data instance
     * @throws IllegalArgumentException if the specified data is not valid
     */
    @NotNull
    BlockData createBlockData(@NotNull String data) throws IllegalArgumentException;

    /**
     * Creates a new {@link BlockData} instance for the specified Material, with
     * all properties initialized to unspecified defaults, except for those
     * provided in data.
     * <br>
     * If <code>material</code> is specified, then the data string must not also
     * contain the material.
     *
     * @param material the material
     * @param data data string
     * @return new data instance
     * @throws IllegalArgumentException if the specified data is not valid
     */
    @NotNull
    @Contract("null, null -> fail")
    BlockData createBlockData(@Nullable Material material, @Nullable String data) throws IllegalArgumentException;

    /**
     * Gets a tag which has already been defined within the server. Plugins are
     * suggested to use the concrete tags in {@link Tag} rather than this method
     * which makes no guarantees about which tags are available, and may also be
     * less performant due to lack of caching.
     * <br>
     * Tags will be searched for in an implementation specific manner, but a
     * path consisting of namespace/tags/registry/key is expected.
     * <br>
     * Server implementations are allowed to handle only the registries
     * indicated in {@link Tag}.
     *
     * @param <T> type of the tag
     * @param registry the tag registry to look at
     * @param tag the name of the tag
     * @param clazz the class of the tag entries
     * @return the tag or null
     */
    @Nullable
    <T extends Keyed> Tag<T> getTag(@NotNull String registry, @NotNull NamespacedKey tag, @NotNull Class<T> clazz);

    /**
     * Gets a all tags which have been defined within the server.
     * <br>
     * Server implementations are allowed to handle only the registries
     * indicated in {@link Tag}.
     * <br>
     * No guarantees are made about the mutability of the returned iterator.
     *
     * @param <T> type of the tag
     * @param registry the tag registry to look at
     * @param clazz the class of the tag entries
     * @return all defined tags
     */
    @NotNull
    <T extends Keyed> Iterable<Tag<T>> getTags(@NotNull String registry, @NotNull Class<T> clazz);

    /**
     * Gets the specified {@link LootTable}.
     *
     * @param key the name of the LootTable
     * @return the LootTable, or null if no LootTable is found with that name
     */
    @Nullable
    LootTable getLootTable(@NotNull NamespacedKey key);

    /**
     * Selects entities using the given Vanilla selector.
     * <br>
     * No guarantees are made about the selector format, other than they match
     * the Vanilla format for the active Minecraft version.
     * <br>
     * Usually a selector will start with '@', unless selecting a Player in
     * which case it may simply be the Player's name or UUID.
     * <br>
     * Note that in Vanilla, elevated permissions are usually required to use
     * '@' selectors, but this method should not check such permissions from the
     * sender.
     *
     * @param sender the sender to execute as, must be provided
     * @param selector the selection string
     * @return a list of the selected entities. The list will not be null, but
     * no further guarantees are made.
     * @throws IllegalArgumentException if the selector is malformed in any way
     * or a parameter is null
     */
    @NotNull
    List<Entity> selectEntities(@NotNull CommandSender sender, @NotNull String selector) throws IllegalArgumentException;

    /**
     * @see UnsafeValues
     * @return the unsafe values instance
     */
    @Deprecated
    @NotNull
    UnsafeValues getUnsafe();
}
