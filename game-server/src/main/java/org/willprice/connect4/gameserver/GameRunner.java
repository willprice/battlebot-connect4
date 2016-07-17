package org.willprice.connect4.gameserver;

import org.willprice.connect4.model.ColumnFullException;
import org.willprice.connect4.model.Connect4;
import org.willprice.connect4.model.NonExistentColumnException;
import org.willprice.connect4.model.players.Player;

/**
 * Takes two players and creates a game of Connect 4 between them
 * until the game is over.
 */
public class GameRunner {
    private final Connect4Factory gameFactory;

    public GameRunner(Connect4Factory gameFactory) {
        this.gameFactory = gameFactory;
    }

    public void startGame(Player player1, Player player2) {
        Connect4 game = gameFactory.createGame(player1, player2);
        while(!game.isOver()) {
            try {
                game.takeTurn();
            } catch (ColumnFullException e) {
                e.printStackTrace();
            } catch (NonExistentColumnException e) {
                e.printStackTrace();
            }
        }
    }
}
