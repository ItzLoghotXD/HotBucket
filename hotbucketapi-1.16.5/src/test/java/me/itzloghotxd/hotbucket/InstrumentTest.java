package me.itzloghotxd.hotbucket;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class InstrumentTest {
    @Test
    public void getByType() {
        for (Instrument instrument : Instrument.values()) {
            assertThat(Instrument.getByType(instrument.getType()), is(instrument));
        }
    }
}
