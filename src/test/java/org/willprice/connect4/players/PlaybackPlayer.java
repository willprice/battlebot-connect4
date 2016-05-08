package org.willprice.connect4.players;

import org.willprice.connect4.ColouredDisc;
import org.willprice.connect4.Connect4;

public class PlaybackPlayer extends Player {
    private final int[] moves;
    private int moveIndex = 0;

    public PlaybackPlayer(ColouredDisc disc, int[] moves) {
        super(disc);
        this.moves = moves;
    }

    @Override
    public int play(Connect4 grid) {
        return moves[moveIndex++];
    }
}
