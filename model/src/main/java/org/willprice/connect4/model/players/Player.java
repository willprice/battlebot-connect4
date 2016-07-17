package org.willprice.connect4.model.players;

import org.willprice.connect4.model.ColouredDisc;
import org.willprice.connect4.model.Connect4;

import java.util.UUID;

public abstract class Player {
    private final ColouredDisc colouredDisk = new ColouredDisc(UUID.randomUUID().toString());

    public abstract int play(Connect4 grid);

    public ColouredDisc getDisk() {
        return colouredDisk;
    }
}
