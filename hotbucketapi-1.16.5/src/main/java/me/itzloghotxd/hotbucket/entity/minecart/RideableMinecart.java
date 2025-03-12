package me.itzloghotxd.hotbucket.entity.minecart;

import me.itzloghotxd.hotbucket.entity.Minecart;

/**
 * Represents a minecart that can have certain {@link
 * me.itzloghotxd.hotbucket.entity.Entity entities} as passengers. Normal passengers
 * include all {@link me.itzloghotxd.hotbucket.entity.LivingEntity living entities} with
 * the exception of {@link me.itzloghotxd.hotbucket.entity.IronGolem iron golems}.
 * Non-player entities that meet normal passenger criteria automatically
 * mount these minecarts when close enough.
 */
public interface RideableMinecart extends Minecart {
}
