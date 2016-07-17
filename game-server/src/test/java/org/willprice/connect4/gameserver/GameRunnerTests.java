package org.willprice.connect4.gameserver;

import org.junit.Test;
import org.mockito.Mockito;
import org.willprice.connect4.model.ColumnFullException;
import org.willprice.connect4.model.Connect4;
import org.willprice.connect4.model.NonExistentColumnException;
import org.willprice.connect4.model.players.Player;

import static org.mockito.Mockito.*;

public class GameRunnerTests {
    @Test
    public void startGameTakesATurn() throws ColumnFullException, NonExistentColumnException {

        Connect4 game = mock(Connect4.class);
        Connect4Factory connect4Factory = new StubConnect4Factory(game);
        GameRunner gameRunner = new GameRunner(connect4Factory);
        when(game.isOver())
                .thenReturn(false)
                .thenReturn(true);

        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);

        gameRunner.startGame(player1, player2);

        verify(game).takeTurn();
    }

    @Test
    public void startGameTakesTurnsUntilGameIsOver() throws ColumnFullException, NonExistentColumnException {
        Connect4 game = mock(Connect4.class);
        Connect4Factory connect4Factory = new StubConnect4Factory(game);
        GameRunner gameRunner = new GameRunner(connect4Factory);
        when(game.isOver())
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(true);

        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);

        gameRunner.startGame(player1, player2);

        verify(game, times(2)).takeTurn();

    }


}
