package me.itzloghotxd.hotbucket;

import com.google.common.collect.Maps;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public enum Instrument {

    /**
     * Piano is the standard instrument for a note block.
     */
    PIANO(0x0),
    /**
     * Bass drum is normally played when a note block is on top of a
     * stone-like block.
     */
    BASS_DRUM(0x1),
    /**
     * Snare drum is normally played when a note block is on top of a sandy
     * block.
     */
    SNARE_DRUM(0x2),
    /**
     * Sticks are normally played when a note block is on top of a glass
     * block.
     */
    STICKS(0x3),
    /**
     * Bass guitar is normally played when a note block is on top of a wooden
     * block.
     */
    BASS_GUITAR(0x4),
    /**
     * Flute is normally played when a note block is on top of a clay block.
     */
    FLUTE(0x5),
    /**
     * Bell is normally played when a note block is on top of a gold block.
     */
    BELL(0x6),
    /**
     * Guitar is normally played when a note block is on top of a woolen block.
     */
    GUITAR(0x7),
    /**
     * Chime is normally played when a note block is on top of a packed ice
     * block.
     */
    CHIME(0x8),
    /**
     * Xylophone is normally played when a note block is on top of a bone block.
     */
    XYLOPHONE(0x9),
    /**
     * Iron Xylophone is normally played when a note block is on top of a iron block.
     */
    IRON_XYLOPHONE(0xA),
    /**
     * Cow Bell is normally played when a note block is on top of a soul sand block.
     */
    COW_BELL(0xB),
    /**
     * Didgeridoo is normally played when a note block is on top of a pumpkin block.
     */
    DIDGERIDOO(0xC),
    /**
     * Bit is normally played when a note block is on top of a emerald block.
     */
    BIT(0xD),
    /**
     * Banjo is normally played when a note block is on top of a hay block.
     */
    BANJO(0xE),
    /**
     * Pling is normally played when a note block is on top of a glowstone block.
     */
    PLING(0xF);

    private final byte type;
    private static final Map<Byte, Instrument> BY_DATA = Maps.newHashMap();

    private Instrument(final int type) {
        this.type = (byte) type;
    }

    /**
     * @return The type ID of this instrument.
     * @deprecated Magic value
     */
    @Deprecated
    public byte getType() {
        return this.type;
    }

    /**
     * Get an instrument by its type ID.
     *
     * @param type The type ID
     * @return The instrument
     * @deprecated Magic value
     */
    @Deprecated
    @Nullable
    public static Instrument getByType(final byte type) {
        return BY_DATA.get(type);
    }

    static {
        for (Instrument instrument : Instrument.values()) {
            BY_DATA.put(instrument.getType(), instrument);
        }
    }
}
