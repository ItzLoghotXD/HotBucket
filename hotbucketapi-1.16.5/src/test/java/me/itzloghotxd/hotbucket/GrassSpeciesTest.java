package me.itzloghotxd.hotbucket;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class GrassSpeciesTest {
    @Test
    public void getByData() {
        for (GrassSpecies grassSpecies : GrassSpecies.values()) {
            assertThat(GrassSpecies.getByData(grassSpecies.getData()), is(grassSpecies));
        }
    }
}
