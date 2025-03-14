package me.itzloghotxd.hotbucket.util.permissions;

import me.itzloghotxd.hotbucket.permissions.Permission;
import me.itzloghotxd.hotbucket.permissions.PermissionDefault;
import org.jetbrains.annotations.NotNull;

public final class CommandPermissions {
    private static final String ROOT = "bukkit.command";
    private static final String PREFIX = ROOT + ".";

    private CommandPermissions() {}

    @NotNull
    public static Permission registerPermissions(@NotNull Permission parent) {
        Permission commands = DefaultPermissions.registerPermission(ROOT, "Gives the user the ability to use all CraftBukkit commands", parent);

        DefaultPermissions.registerPermission(PREFIX + "help", "Allows the user to view the vanilla help menu", PermissionDefault.TRUE, commands);
        DefaultPermissions.registerPermission(PREFIX + "plugins", "Allows the user to view the list of plugins running on this server", PermissionDefault.TRUE, commands);
        DefaultPermissions.registerPermission(PREFIX + "reload", "Allows the user to reload the server settings", PermissionDefault.OP, commands);
        DefaultPermissions.registerPermission(PREFIX + "version", "Allows the user to view the version of the server", PermissionDefault.TRUE, commands);

        commands.recalculatePermissibles();
        return commands;
    }
}
