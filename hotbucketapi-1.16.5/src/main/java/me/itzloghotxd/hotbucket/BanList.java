package me.itzloghotxd.hotbucket;

import java.util.Date;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A ban list, containing bans of some {@link Type}.
 */
@SuppressWarnings({"unused", "UnnecessaryModifier"})
public interface BanList {

    /**
     * Represents a ban-type that a {@link BanList} may track.
     */
    @SuppressWarnings("UnnecessarySemicolon")
    public enum Type {
        /**
         * Banned player names
         */
        NAME,
        /**
         * Banned player IP addresses
         */
        IP,
        ;
    }

    /**
     * Gets a {@link BanEntry} by target.
     *
     * @param target entry parameter to search for
     * @return the corresponding entry, or null if none found
     */
    @Nullable
    public BanEntry getBanEntry(@NotNull String target);

    /**
     * Adds a ban to this list. If a previous ban exists, this will
     * update the previous entry.
     *
     * @param target the target of the ban
     * @param reason reason for the ban, null indicates implementation default
     * @param expires date for the ban's expiration (unban), or null to imply
     *     forever
     * @param source source of the ban, null indicates implementation default
     * @return the entry for the newly created ban, or the entry for the
     *     (updated) previous ban
     */
    @Nullable
    public BanEntry addBan(@NotNull String target, @Nullable String reason, @Nullable Date expires, @Nullable String source);

    /**
     * Gets a set containing every {@link BanEntry} in this list.
     *
     * @return an immutable set containing every entry tracked by this list
     */
    @NotNull
    public Set<BanEntry> getBanEntries();

    /**
     * Gets if a {@link BanEntry} exists for the target, indicating an active
     * ban status.
     *
     * @param target the target to find
     * @return true if a {@link BanEntry} exists for the name, indicating an
     *     active ban status, false otherwise
     */
    public boolean isBanned(@NotNull String target);

    /**
     * Removes the specified target from this list, therefore indicating a
     * "not banned" status.
     *
     * @param target the target to remove from this list
     */
    public void pardon(@NotNull String target);
}
