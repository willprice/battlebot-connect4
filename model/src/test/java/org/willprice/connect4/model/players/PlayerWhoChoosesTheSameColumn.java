package org.willprice.connect4.model.players;

import org.willprice.connect4.model.ColouredDisc;
import org.willprice.connect4.model.Connect4;

public class PlayerWhoChoosesTheSameColumn extends Player {
    private final int targetColumnIndex;

    public PlayerWhoChoosesTheSameColumn(int targetColumnIndex) {
        this.targetColumnIndex = targetColumnIndex;
    }

    @Override
    public int play(Connect4 grid) {
        return targetColumnIndex;
    }
}
