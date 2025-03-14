package me.itzloghotxd.hotbucket.event.server;

import java.util.List;
import org.apache.commons.lang.Validate;
import me.itzloghotxd.hotbucket.command.CommandSender;
import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.event.Event;
import me.itzloghotxd.hotbucket.event.HandlerList;
import me.itzloghotxd.hotbucket.event.player.PlayerCommandSendEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a {@link CommandSender} of any description (ie: player or
 * console) attempts to tab complete.
 * <br>
 * Note that due to client changes, if the sender is a Player, this event will
 * only begin to fire once command arguments are specified, not commands
 * themselves. Plugins wishing to remove commands from tab completion are
 * advised to ensure the client does not have permission for the relevant
 * commands, or use {@link PlayerCommandSendEvent}.
 */
public class TabCompleteEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    //
    private final CommandSender sender;
    private final String buffer;
    private List<String> completions;
    private boolean cancelled;

    public TabCompleteEvent(@NotNull CommandSender sender, @NotNull String buffer, @NotNull List<String> completions) {
        Validate.notNull(sender, "sender");
        Validate.notNull(buffer, "buffer");
        Validate.notNull(completions, "completions");

        this.sender = sender;
        this.buffer = buffer;
        this.completions = completions;
    }

    /**
     * Get the sender completing this command.
     *
     * @return the {@link CommandSender} instance
     */
    @NotNull
    public CommandSender getSender() {
        return sender;
    }

    /**
     * Return the entire buffer which formed the basis of this completion.
     *
     * @return command buffer, as entered
     */
    @NotNull
    public String getBuffer() {
        return buffer;
    }

    /**
     * The list of completions which will be offered to the sender, in order.
     * This list is mutable and reflects what will be offered.
     *
     * @return a list of offered completions
     */
    @NotNull
    public List<String> getCompletions() {
        return completions;
    }

    /**
     * Set the completions offered, overriding any already set.
     *
     * @param completions the new completions
     */
    public void setCompletions(@NotNull List<String> completions) {
        Validate.notNull(completions);
        this.completions = completions;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
