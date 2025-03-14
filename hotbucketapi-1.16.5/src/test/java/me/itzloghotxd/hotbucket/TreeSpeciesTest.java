package me.itzloghotxd.hotbucket;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class TreeSpeciesTest {
    @Test
    public void getByData() {
        for (TreeSpecies treeSpecies : TreeSpecies.values()) {
            assertThat(TreeSpecies.getByData(treeSpecies.getData()), is(treeSpecies));
        }
    }
}
