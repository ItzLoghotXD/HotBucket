package me.itzloghotxd.hotbucket;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class CoalTypeTest {
    @Test
    public void getByData() {
        for (CoalType coalType : CoalType.values()) {
            assertThat(CoalType.getByData(coalType.getData()), is(coalType));
        }
    }
}
