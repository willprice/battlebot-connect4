package org.willprice.connect4.gameserver;

import org.willprice.connect4.model.Connect4;
import org.willprice.connect4.model.players.Player;

public class Connect4FactoryImpl implements Connect4Factory {
    @Override
    public Connect4 createGame(Player player1, Player player2) {
        return new Connect4(player1, player2);
    }
}
