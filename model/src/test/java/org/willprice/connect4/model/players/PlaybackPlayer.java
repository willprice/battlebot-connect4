package org.willprice.connect4.model.players;

import org.willprice.connect4.model.ColouredDisc;
import org.willprice.connect4.model.Connect4;

public class PlaybackPlayer extends Player {
    private final int[] moves;
    private int moveIndex;

    public PlaybackPlayer(int[] moves) {
        this.moves = moves;
    }

    @Override
    public int play(Connect4 grid) {
        return moves[moveIndex++];
    }
}
