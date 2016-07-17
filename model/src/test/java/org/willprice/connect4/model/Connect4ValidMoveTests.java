package org.willprice.connect4.model;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.willprice.connect4.model.players.Player;
import org.willprice.connect4.model.players.PlayerWhoChoosesTheSameColumn;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class Connect4ValidMoveTests extends AbstractConnect4Tests {
    @Test
    @Parameters
    public void tokenCountTests(Player player1, Player player2, int numberOfTurns, int[][] columnCounts) throws ColumnFullException, NonExistentColumnException {
        Connect4 connect4 = new Connect4(player1, player2);

        takeTurns(connect4, numberOfTurns);

        for (int[] pair : columnCounts) {
            int columnIndex = pair[0];
            int expectedNumberOfTokens = pair[1];
            assertEquals(expectedNumberOfTokens, connect4.getNumberOfTokens(columnIndex));
        }
    }

    public Object[] parametersForTokenCountTests() {
        return new Object[]{
                new Object[]{
                        new PlayerWhoChoosesTheSameColumn(0),
                        null,
                        1,
                        new int[][]{
                                // column, expectedCount
                                new int[]{0, 1},
                        }
                },
                new Object[]{
                        new PlayerWhoChoosesTheSameColumn(0),
                        new PlayerWhoChoosesTheSameColumn(1),
                        2,
                        new int[][]{
                                // column, expectedCount
                                new int[]{0, 1},
                                new int[]{1, 1},
                        }
                },
        };
    }

}
