package me.itzloghotxd.hotbucket.event;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import com.google.common.collect.ImmutableList;
import me.itzloghotxd.hotbucket.event.player.PlayerChatTabCompleteEvent;
import me.itzloghotxd.hotbucket.plugin.messaging.TestPlayer;
import org.junit.Test;

public class PlayerChatTabCompleteEventTest {

    @Test
    public void testGetLastToken() {
        assertThat(getToken("Hello everyone!"), is("everyone!"));
        assertThat(getToken(" welcome to the show..."), is("show..."));
        assertThat(getToken("The whitespace is here "), is(""));
        assertThat(getToken("Too much whitespace is here  "), is(""));
        assertThat(getToken("The_whitespace_is_missing"), is("The_whitespace_is_missing"));
        assertThat(getToken(""), is(""));
        assertThat(getToken(" "), is(""));
    }

    private String getToken(String message) {
        return new PlayerChatTabCompleteEvent(TestPlayer.getInstance(), message, ImmutableList.<String>of()).getLastToken();
    }
}
