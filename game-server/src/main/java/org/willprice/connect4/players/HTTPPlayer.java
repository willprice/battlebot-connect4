package org.willprice.connect4.players;

import org.willprice.connect4.model.ColouredDisc;
import org.willprice.connect4.model.Connect4;
import org.willprice.connect4.model.players.Player;

import java.net.URL;

public class HTTPPlayer extends Player {
    private final String host;
    private final int port;

    public HTTPPlayer(String host, int port) {
        super(null);
        this.host = host;
        this.port = port;
    }

    public HTTPPlayer(URL url) {
        this(url.getHost(), url.getPort());
    }

    @Override
    public int play(Connect4 grid) {
        return 0;
    }

    public String getAddress() {
        return host + ":" + port;
    }
}
