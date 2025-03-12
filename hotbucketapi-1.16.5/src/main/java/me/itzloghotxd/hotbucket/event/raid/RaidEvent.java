package me.itzloghotxd.hotbucket.event.raid;

import me.itzloghotxd.hotbucket.Raid;
import me.itzloghotxd.hotbucket.World;
import me.itzloghotxd.hotbucket.event.world.WorldEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Represents events related to raids.
 */
public abstract class RaidEvent extends WorldEvent {

    private final Raid raid;

    protected RaidEvent(@NotNull Raid raid, @NotNull World world) {
        super(world);
        this.raid = raid;
    }

    /**
     * Returns the raid involved with this event.
     *
     * @return Raid
     */
    @NotNull
    public Raid getRaid() {
        return raid;
    }
}
