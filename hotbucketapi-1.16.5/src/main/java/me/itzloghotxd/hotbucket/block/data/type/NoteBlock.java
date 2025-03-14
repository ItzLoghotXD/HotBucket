package me.itzloghotxd.hotbucket.block.data.type;

import me.itzloghotxd.hotbucket.Instrument;
import me.itzloghotxd.hotbucket.Note;
import me.itzloghotxd.hotbucket.block.data.Powerable;
import org.jetbrains.annotations.NotNull;

/**
 * 'instrument' is the type of sound made when this note block is activated.
 * <br>
 * 'note' is the specified tuned pitch that the instrument will be played in.
 */
public interface NoteBlock extends Powerable {

    /**
     * Gets the value of the 'instrument' property.
     *
     * @return the 'instrument' value
     */
    @NotNull
    Instrument getInstrument();

    /**
     * Sets the value of the 'instrument' property.
     *
     * @param instrument the new 'instrument' value
     */
    void setInstrument(@NotNull Instrument instrument);

    /**
     * Gets the value of the 'note' property.
     *
     * @return the 'note' value
     */
    @NotNull
    Note getNote();

    /**
     * Sets the value of the 'note' property.
     *
     * @param note the new 'note' value
     */
    void setNote(@NotNull Note note);
}
