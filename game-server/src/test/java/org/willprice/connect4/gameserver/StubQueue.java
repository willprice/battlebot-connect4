package org.willprice.connect4.gameserver;

import org.willprice.connect4.model.players.Player;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class StubQueue implements Queue {
    private final PlayerPair players;

    public StubQueue(PlayerPair players) {
        this.players = players;
    }

    @Override
    public PlayerPair get() {
        return players;
    }

    @Override
    public void add(Player player) {
    }
}
