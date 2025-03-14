package me.itzloghotxd.hotbucket;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class EffectTest {
    @Test
    public void getById() {
        for (Effect effect : Effect.values()) {
            assertThat(Effect.getById(effect.getId()), is(effect));
        }
    }
}
