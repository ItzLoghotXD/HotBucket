package me.itzloghotxd.hotbucket;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class WorldTypeTest {
    @Test
    public void getByName() {
        for (WorldType worldType : WorldType.values()) {
            assertThat(WorldType.getByName(worldType.getName()), is(worldType));
        }
    }
}
