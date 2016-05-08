package org.willprice.connect4.players;

import org.willprice.connect4.ColouredDisc;
import org.willprice.connect4.Connect4;

public abstract class Player {
    private final ColouredDisc colouredDisk;

    Player(ColouredDisc disc) {
        this.colouredDisk = disc;
    }
    public abstract int play(Connect4 grid);
    public ColouredDisc getDisk() {
        return colouredDisk;
    }
}
