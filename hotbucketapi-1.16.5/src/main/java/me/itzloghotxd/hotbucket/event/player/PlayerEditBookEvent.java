package me.itzloghotxd.hotbucket.event.player;

import org.apache.commons.lang.Validate;
import me.itzloghotxd.hotbucket.Bukkit;
import me.itzloghotxd.hotbucket.entity.Player;
import me.itzloghotxd.hotbucket.event.Cancellable;
import me.itzloghotxd.hotbucket.event.HandlerList;
import me.itzloghotxd.hotbucket.inventory.meta.BookMeta;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a player edits or signs a book and quill item. If the event is
 * cancelled, no changes are made to the BookMeta
 */
public class PlayerEditBookEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private final BookMeta previousBookMeta;
    private final int slot;
    private BookMeta newBookMeta;
    private boolean isSigning;
    private boolean cancel;

    public PlayerEditBookEvent(@NotNull Player who, int slot, @NotNull BookMeta previousBookMeta, @NotNull BookMeta newBookMeta, boolean isSigning) {
        super(who);

        Validate.isTrue(slot >= -1 && slot <= 8, "Slot must be in range (-1)-8 inclusive");
        Validate.notNull(previousBookMeta, "Previous book meta must not be null");
        Validate.notNull(newBookMeta, "New book meta must not be null");

        Bukkit.getItemFactory().equals(previousBookMeta, newBookMeta);

        this.previousBookMeta = previousBookMeta;
        this.newBookMeta = newBookMeta;
        this.slot = slot;
        this.isSigning = isSigning;
        this.cancel = false;
    }

    /**
     * Gets the book meta currently on the book.
     * <p>
     * Note: this is a copy of the book meta. You cannot use this object to
     * change the existing book meta.
     *
     * @return the book meta currently on the book
     */
    @NotNull
    public BookMeta getPreviousBookMeta() {
        return previousBookMeta.clone();
    }

    /**
     * Gets the book meta that the player is attempting to add to the book.
     * <p>
     * Note: this is a copy of the proposed new book meta. Use {@link
     * #setNewBookMeta(BookMeta)} to change what will actually be added to the
     * book.
     *
     * @return the book meta that the player is attempting to add
     */
    @NotNull
    public BookMeta getNewBookMeta() {
        return newBookMeta.clone();
    }

    /**
     * Gets the inventory slot number for the book item that triggered this
     * event.
     * <p>
     * This is a slot number on the player's hotbar in the range 0-8, or -1 for
     * off hand.
     *
     * @return the inventory slot number that the book item occupies
     * @deprecated books may be signed from off hand
     */
    @Deprecated
    public int getSlot() {
        return slot;
    }

    /**
     * Sets the book meta that will actually be added to the book.
     *
     * @param newBookMeta new book meta
     * @throws IllegalArgumentException if the new book meta is null
     */
    public void setNewBookMeta(@NotNull BookMeta newBookMeta) throws IllegalArgumentException {
        Validate.notNull(newBookMeta, "New book meta must not be null");
        Bukkit.getItemFactory().equals(newBookMeta, null);
        this.newBookMeta = newBookMeta.clone();
    }

    /**
     * Gets whether or not the book is being signed. If a book is signed the
     * Material changes from BOOK_AND_QUILL to WRITTEN_BOOK.
     *
     * @return true if the book is being signed
     */
    public boolean isSigning() {
        return isSigning;
    }

    /**
     * Sets whether or not the book is being signed. If a book is signed the
     * Material changes from BOOK_AND_QUILL to WRITTEN_BOOK.
     *
     * @param signing whether or not the book is being signed.
     */
    public void setSigning(boolean signing) {
        isSigning = signing;
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

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
