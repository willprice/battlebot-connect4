package org.willprice.connect4.model.players;

import org.willprice.connect4.model.ColouredDisc;
import org.willprice.connect4.model.Connect4;

public abstract class Player {
    private final ColouredDisc colouredDisk;

    protected Player(ColouredDisc disc) {
        this.colouredDisk = disc;
    }
    public abstract int play(Connect4 grid);
    public ColouredDisc getDisk() {
        return colouredDisk;
    }
}
