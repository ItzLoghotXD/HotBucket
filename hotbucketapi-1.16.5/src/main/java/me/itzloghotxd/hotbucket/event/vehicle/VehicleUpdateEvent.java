package me.itzloghotxd.hotbucket.event.vehicle;

import me.itzloghotxd.hotbucket.entity.Vehicle;
import me.itzloghotxd.hotbucket.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a vehicle updates
 */
public class VehicleUpdateEvent extends VehicleEvent {
    private static final HandlerList handlers = new HandlerList();

    public VehicleUpdateEvent(@NotNull final Vehicle vehicle) {
        super(vehicle);
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
