package org.willprice.connect4.gameserver;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class GameServerTests {
    @Test
    public void dontWaitBetweenChecksByDefault() {
        GameServer server = new GameServer(mock(GameCoordinator.class));

        assertFalse(server.isWaitingBetweenChecks());
    }

    @Test
    public void whenNoGameStartedThenWaitBetweenChecks() {
        GameServer server = new GameServer(mock(GameCoordinator.class));

        server.noGameStarted();

        assertTrue(server.isWaitingBetweenChecks());
    }

    @Test
    public void whenGameStartedThenDontWaitBetweenChecks() {
        GameServer server = new GameServer(mock(GameCoordinator.class));

        server.gameStarted();

        assertFalse(server.isWaitingBetweenChecks());
    }
}
