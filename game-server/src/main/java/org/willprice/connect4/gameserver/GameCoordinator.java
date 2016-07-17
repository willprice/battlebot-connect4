package org.willprice.connect4.gameserver;

import org.willprice.connect4.model.players.Player;

/**
 * Takes a queue of players and a game runner and picks pairs of players
 * from the queue and passes them to the game runner to play a game.
 */
public class GameCoordinator {
    private final Queue queue;
    private final GameRunner gameRunner;
    private final GameListener listener;

    public GameCoordinator(Queue queue, GameRunner gameRunner, GameListener listener) {
        this.queue = queue;
        this.gameRunner = gameRunner;
        this.listener = listener;
    }

    public void check() {
        PlayerPair players = queue.get();
        if (players != null) {
            gameRunner.startGame(players.getPlayer1(), players.getPlayer2());
            listener.gameStarted();
        } else {
            listener.noGameStarted();
        }
    }

    public void join(Player player) {
        queue.add(player);
    }
}
