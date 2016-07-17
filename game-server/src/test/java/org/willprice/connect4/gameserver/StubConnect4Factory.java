package org.willprice.connect4.gameserver;

import org.willprice.connect4.model.Connect4;
import org.willprice.connect4.model.players.Player;

public class StubConnect4Factory implements Connect4Factory {
    private final Connect4 game;

    public StubConnect4Factory(Connect4 game) {
        this.game = game;
    }


    @Override
    public Connect4 createGame(Player player1, Player player2) {
        return game;
    }
}
