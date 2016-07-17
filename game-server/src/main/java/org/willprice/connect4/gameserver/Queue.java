package org.willprice.connect4.gameserver;

import org.willprice.connect4.model.players.Player;

public interface Queue<T extends Player> {
    PlayerPair get();

    void add(T player);
}
