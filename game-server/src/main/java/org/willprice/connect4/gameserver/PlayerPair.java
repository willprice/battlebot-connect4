package org.willprice.connect4.gameserver;

import org.willprice.connect4.model.players.Player;

/**
 * Used when picking pairs of players off the queue to initiate a new game.
 */
public class PlayerPair {
    private final Player player1;
    private final Player player2;

    public PlayerPair(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
