package org.willprice.connect4.players;

import org.willprice.connect4.ColouredDisc;
import org.willprice.connect4.Connect4;

public class PlayerWhoChoosesTheSameColumn extends Player {
    private final int targetColumnIndex;

    public PlayerWhoChoosesTheSameColumn(ColouredDisc colouredDisc, int targetColumnIndex) {
        super(colouredDisc);
        this.targetColumnIndex = targetColumnIndex;
    }

    @Override
    public int play(Connect4 grid) {
        return targetColumnIndex;
    }
}
