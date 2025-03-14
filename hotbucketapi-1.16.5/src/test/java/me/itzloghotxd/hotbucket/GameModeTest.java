package me.itzloghotxd.hotbucket;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class GameModeTest {
    @Test
    public void getByValue() {
        for (GameMode gameMode : GameMode.values()) {
            assertThat(GameMode.getByValue(gameMode.getValue()), is(gameMode));
        }
    }
}
