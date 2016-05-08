package org.willprice.connect4;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.willprice.connect4.players.PlaybackPlayer;
import org.willprice.connect4.players.Player;
import org.willprice.connect4.players.PlayerWhoChoosesTheSameColumn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class Connect4WinningMoveTests extends AbstractConnect4Tests {
    @Test
    @Parameters
    public void moveCauseWin(Player player1, Player player2, int numberOfTurnsToWin) throws ColumnFullException, NonExistentColumnException {
        Connect4 game = new Connect4(player1, player2);
        takeTurns(game, numberOfTurnsToWin - 1);
        assertFalse(game.isOver());
        takeTurns(game, 1);
        assertTrue(game.isOver());
    }

    public Object[] parametersForMoveCauseWin() {
        return new Object[]{
                new Object[]{
                        new PlayerWhoChoosesTheSameColumn(RED, 0),
                        new PlayerWhoChoosesTheSameColumn(YELLOW, 1),
                        7,
                },
                new Object[]{
                        new PlayerWhoChoosesTheSameColumn(RED, 1),
                        new PlayerWhoChoosesTheSameColumn(YELLOW, 2),
                        7,
                },
                new Object[]{
                        new PlaybackPlayer(RED, new int[]{2, 1, 1, 1, 1}),
                        new PlaybackPlayer(YELLOW, new int[]{3, 2, 2, 2}),
                        9,
                },
                new Object[]{
                        new PlaybackPlayer(RED, new int[]{0, 1, 2, 3}),
                        new PlaybackPlayer(YELLOW, new int[]{4, 4, 4}),
                        7,
                },
                new Object[]{
                        new PlaybackPlayer(RED, new int[]{1, 2, 3, 4}),
                        new PlaybackPlayer(YELLOW, new int[]{5, 5, 5}),
                        7,
                },
                //           y
                // - r r r r y
                // - r y r y y
                // -----------
                // 0 1 2 3 4 5
                new Object[]{
                        new PlaybackPlayer(RED, new int[]{1, 1, 2, 3, 3, 4}),
                        new PlaybackPlayer(YELLOW, new int[]{2, 4, 5, 5, 5}),
                        11,
                },
                //       r
                //     r r
                // y r y y
                // r y y y
                // -------
                // 0 1 2 3
                // placing disk in winning run at position 3 last
                new Object[]{
                        new PlaybackPlayer(RED, new int[]{0, 1, 2, 2, 3, 3}),
                        new PlaybackPlayer(YELLOW, new int[]{1, 2, 3, 3, 0}),
                        11,
                },
                //       r
                //     r y
                // y r y r
                // r y r y
                // -------
                // 0 1 2 3
                // placing disk in winning run at position 2 last
                new Object[]{
                        new PlaybackPlayer(RED, new int[]{0, 1, 3, 3, 2, 2}),
                        new PlaybackPlayer(YELLOW, new int[]{1, 3, 3, 2, 0}),
                        11,
                },
                //       r
                //     r y
                // y r y r
                // r y r y
                // -------
                // 0 1 2 3
                // placing disk in winning run at position 1 last
                new Object[]{
                        new PlaybackPlayer(RED, new int[]{0, 2, 3, 3, 2, 1}),
                        new PlaybackPlayer(YELLOW, new int[]{1, 3, 3, 2, 0}),
                        11,
                },
                //       r
                //   y r y
                //   r y r
                // r y r y
                // -------
                // 0 1 2 3
                // placing disk in winning run at position 0 last
                new Object[]{
                        new PlaybackPlayer(RED, new int[]{2, 1, 3, 3, 2, 0}),
                        new PlaybackPlayer(YELLOW, new int[]{1, 3, 3, 2, 1}),
                        11,
                },
                //         r
                //       r r
                //   y r r y
                //   r y y y
                // ---------
                // 0 1 2 3 4
                // placing disk in winning run at position 3 last
                new Object[]{
                        new PlaybackPlayer(RED, new int[]{1, 2, 3, 3, 4, 4}),
                        new PlaybackPlayer(YELLOW, new int[]{2, 3, 4, 4, 1}),
                        11,
                },
                //             r
                //           r r
                //       y r r y
                //       r y y y
                // -------------
                // 0 1 2 3 4 5 6
                // placing disk in winning run at position 3 last
                new Object[]{
                        new PlaybackPlayer(RED, new int[]{3, 4, 5, 5, 6, 6}),
                        new PlaybackPlayer(YELLOW, new int[]{4, 5, 6, 6, 3}),
                        11,
                },
                // 5 |       r
                // 4 |     r y
                // 3 | y r y r
                // 2 | r y r y
                // 1 | r y r y
                // 0 | r y r y
                // --|--------
                //   | 0 1 2 3
                // placing disk in winning run at position 0 last
                new Object[]{
                        new PlaybackPlayer(RED, new int[]{0, 2, 0, 2, 0, 2, 3, 1, 2, 3}),
                        new PlaybackPlayer(YELLOW, new int[]{1, 3, 1, 3, 1, 3, 2, 0, 3}),
                        19,
                },
                // r
                // r r
                // y r r y
                // y y y r
                // -------
                // 0 1 2 3
                // placing disk in winning run at position 3 last
                new Object[]{
                        new PlaybackPlayer(RED, new int[]{3, 2, 1, 1, 0, 0}),
                        new PlaybackPlayer(YELLOW, new int[]{2, 1, 0, 0, 3}),
                        11,
                },
        };
    }
}
