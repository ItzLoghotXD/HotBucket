package me.itzloghotxd.hotbucket.event.vehicle;

import me.itzloghotxd.hotbucket.entity.Vehicle;
import org.jetbrains.annotations.NotNull;

/**
 * Raised when a vehicle collides.
 */
public abstract class VehicleCollisionEvent extends VehicleEvent {
    public VehicleCollisionEvent(@NotNull final Vehicle vehicle) {
        super(vehicle);
    }
}
