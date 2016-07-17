package org.willprice.connect4.gameserver;

import org.willprice.connect4.model.Connect4;
import org.willprice.connect4.model.players.Player;

public interface Connect4Factory {
    Connect4 createGame(Player player1, Player player2);
}
