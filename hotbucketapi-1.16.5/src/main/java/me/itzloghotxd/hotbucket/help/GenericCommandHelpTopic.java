package me.itzloghotxd.hotbucket.help;

import org.apache.commons.lang.StringUtils;
import me.itzloghotxd.hotbucket.ChatColor;
import me.itzloghotxd.hotbucket.command.Command;
import me.itzloghotxd.hotbucket.command.CommandSender;
import me.itzloghotxd.hotbucket.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * Lacking an alternative, the help system will create instances of
 * GenericCommandHelpTopic for each command in the server's CommandMap. You
 * can use this class as a base class for custom help topics, or as an example
 * for how to write your own.
 */
public class GenericCommandHelpTopic extends HelpTopic {

    protected Command command;

    public GenericCommandHelpTopic(@NotNull Command command) {
        this.command = command;

        if (command.getLabel().startsWith("/")) {
            name = command.getLabel();
        } else {
            name = "/" + command.getLabel();
        }

        // The short text is the first line of the description
        int i = command.getDescription().indexOf('\n');
        if (i > 1) {
            shortText = command.getDescription().substring(0, i - 1);
        } else {
            shortText = command.getDescription();
        }

        // Build full text
        StringBuilder sb = new StringBuilder();

        sb.append(ChatColor.GOLD);
        sb.append("Description: ");
        sb.append(ChatColor.WHITE);
        sb.append(command.getDescription());

        sb.append("\n");

        sb.append(ChatColor.GOLD);
        sb.append("Usage: ");
        sb.append(ChatColor.WHITE);
        sb.append(command.getUsage().replace("<command>", name.substring(1)));

        if (command.getAliases().size() > 0) {
            sb.append("\n");
            sb.append(ChatColor.GOLD);
            sb.append("Aliases: ");
            sb.append(ChatColor.WHITE);
            sb.append(ChatColor.WHITE + StringUtils.join(command.getAliases(), ", "));
        }
        fullText = sb.toString();
    }

    @Override
    public boolean canSee(@NotNull CommandSender sender) {
        if (!command.isRegistered()) {
            // Unregistered commands should not show up in the help
            return false;
        }

        if (sender instanceof ConsoleCommandSender) {
            return true;
        }

        if (amendedPermission != null) {
            return sender.hasPermission(amendedPermission);
        } else {
            return command.testPermissionSilent(sender);
        }
    }
}
