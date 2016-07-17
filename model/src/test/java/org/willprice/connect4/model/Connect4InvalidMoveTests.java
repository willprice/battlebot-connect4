package org.willprice.connect4.model;

import org.junit.Test;
import org.willprice.connect4.model.players.Player;
import org.willprice.connect4.model.players.PlayerWhoChoosesTheSameColumn;

public class Connect4InvalidMoveTests extends AbstractConnect4Tests {
    private static final ColouredDisc RED = new ColouredDisc("Red");
    private static final ColouredDisc YELLOW = new ColouredDisc("Yellow");

    @Test(expected = ColumnFullException.class)
    public void aPlayerPlacingAColouredDiscInAFullColumnThrowsColumnFullException() throws ColumnFullException, NonExistentColumnException {
        Player player1 = new PlayerWhoChoosesTheSameColumn(RED, 0);
        Player player2 = new PlayerWhoChoosesTheSameColumn(YELLOW, 0);
        Connect4 game = new Connect4(player1, player2);
        int numberOfTurns = 7;
        takeTurns(game, numberOfTurns);
    }

    @Test(expected = NonExistentColumnException.class)
    public void aPlayerPlacingAColouredDiscOutsideTheFurthestColumnThrowsNonExistentColumnException() throws ColumnFullException, NonExistentColumnException {
        Player player1 = new PlayerWhoChoosesTheSameColumn(RED, 7);
        Connect4 game = new Connect4(player1, null);
        takeTurns(game, 1);
    }
}
