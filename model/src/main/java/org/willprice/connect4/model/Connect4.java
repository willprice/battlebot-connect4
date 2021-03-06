package org.willprice.connect4.model;

import org.willprice.connect4.model.players.Player;

public class Connect4 {
	private final int COLS = 7;
    private final int ROWS = 6;
    private final int RUN_LENGTH = 4;

    private final Grid grid = new Grid(COLS, ROWS, RUN_LENGTH);
	private final Player player1;
    private final Player player2;
    private Player nextPlayer;

    public Connect4(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        nextPlayer = player1;
    }

    public void takeTurn() throws ColumnFullException, NonExistentColumnException {
        int columnIndex = nextPlayer.play(this);
        grid.insertDiscInColumn(nextPlayer.getDisk(), columnIndex);
        updateNextPlayer();
    }

	private void updateNextPlayer() {
        if (nextPlayer == player1) {
            nextPlayer = player2;
        } else {
            nextPlayer = player1;
        }
    }

	public boolean isOver() {
		return grid.isOver();
	}

	public int getNumberOfTokens(int columnIndex) {
		return grid.getNumberOfTokens(columnIndex);
	}
}
