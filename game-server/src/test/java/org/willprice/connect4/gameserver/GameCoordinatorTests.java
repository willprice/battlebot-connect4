package org.willprice.connect4.gameserver;

import org.junit.Test;
import org.willprice.connect4.model.players.Player;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class GameCoordinatorTests {
    @Test
    public void whenThereAreTwoPlayersOnTheQueueTheyPlayAGame() {
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);

        PlayerPair players = new PlayerPair(player1, player2);
        Queue queue = new StubQueue(players);
        GameRunner gameRunner = mock(GameRunner.class);
        GameCoordinator coordinator = new GameCoordinator(queue, gameRunner, mock(GameListener.class));

        coordinator.check();

        verify(gameRunner).startGame(player1, player2);
    }

    @Test
    public void whenThereAreFewerThanTwoPlayersOnTheQueueThenNoGameIsStarted() {
        GameRunner gameRunner = mock(GameRunner.class);
        Queue queue = new StubQueue(null);
        GameCoordinator coordinator = new GameCoordinator(queue, gameRunner, mock(GameListener.class));

        coordinator.check();

        verify(gameRunner, never()).startGame(any(), any());
    }

    @Test
    public void whenAGameIsNotStartedThenCheckNotifiesListenerNoGameWasStarted() {
        Queue queue = new StubQueue(null);
        GameListener listener = mock(GameListener.class);
        GameCoordinator coordinator = new GameCoordinator(queue, null, listener);

        coordinator.check();

        verify(listener).noGameStarted();
    }

    @Test
    public void whenAGameIsStartedThenCheckNotifiesListenerAGameWasStarted() {
        Queue queue = new StubQueue(new PlayerPair(mock(Player.class), mock(Player.class)));
        GameRunner gameRunner = mock(GameRunner.class);
        GameListener listener = mock(GameListener.class);
        GameCoordinator coordinator = new GameCoordinator(queue, gameRunner, listener);

        coordinator.check();

        verify(listener).gameStarted();
    }

    @Test
    public void whenAGameIsStartedThenCheckDoesNotNotifyListenerNoGameWasStarted() {
        Queue queue = new StubQueue(new PlayerPair(mock(Player.class), mock(Player.class)));
        GameListener listener = mock(GameListener.class);
        GameCoordinator coordinator = new GameCoordinator(queue, mock(GameRunner.class), listener);

        coordinator.check();

        verify(listener, never()).noGameStarted();
    }

    @Test
    public void whenThePlayerJoinsItIsAddedTheQueue() {
        Player player = mock(Player.class);
        Queue queue = mock(Queue.class);
        GameRunner gameRunner = mock(GameRunner.class);
        GameCoordinator coordinator = new GameCoordinator(queue, gameRunner, null);

        coordinator.join(player);

        verify(queue).add(player);
    }
}
