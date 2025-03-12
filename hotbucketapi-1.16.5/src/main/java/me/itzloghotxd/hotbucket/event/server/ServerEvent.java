package me.itzloghotxd.hotbucket.event.server;

import me.itzloghotxd.hotbucket.event.Event;

/**
 * Miscellaneous server events
 */
public abstract class ServerEvent extends Event {

    public ServerEvent() {
        super();
    }

    public ServerEvent(boolean isAsync) {
        super(isAsync);
    }
}
