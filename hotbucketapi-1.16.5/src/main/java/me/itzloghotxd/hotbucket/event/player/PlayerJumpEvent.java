package me.itzloghotxd.hotbucket.event.player;

import me.itzloghotxd.hotbucket.entity.Player;
import me.itzloghotxd.hotbucket.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerJumpEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();

    public PlayerJumpEvent(@NotNull Player who) {
        super(who);
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
