package org.willprice.connect4;

import org.willprice.connect4.players.Player;

import java.util.ArrayList;
import java.util.List;

public class Connect4 {
    private static final int WIDTH = 7;
    private static final int HEIGHT = 6;
    private static final int RUN_LENGTH = 4;

    private ColouredDisc[][] grid = new ColouredDisc[WIDTH][HEIGHT];
    private final Player player1;
    private final Player player2;
    private Player nextPlayer;
    private boolean over;
    private int lastColumnUsed;
    private int lastRowUsed;

    public Connect4(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.nextPlayer = player1;
    }

    public int getNumberOfTokens(int columnIndex) {
        int numberOfTokens = 0;
        while(numberOfTokens < grid[columnIndex].length && grid[columnIndex][numberOfTokens] != null) {
            numberOfTokens++;
        }
        return numberOfTokens;
    }

    public void takeTurn() throws ColumnFullException, NonExistentColumnException {
        int columnIndex = nextPlayer.play(this);
        checkColumnExists(columnIndex);
        int nextFreeRowIndex = getNumberOfTokens(columnIndex);
        checkColumnIsntFull(nextFreeRowIndex);
        grid[columnIndex][nextFreeRowIndex] = nextPlayer.getDisk();
        lastColumnUsed = columnIndex;
        lastRowUsed = nextFreeRowIndex;
        updateNextPlayer();
    }

    private void checkColumnIsntFull(int rowIndex) throws ColumnFullException {
        if (rowIndex >= HEIGHT) {
            throw new ColumnFullException();
        }
    }

    private void checkColumnExists(int columnIndex) throws NonExistentColumnException {
        if (columnIndex >= WIDTH) {
            throw new NonExistentColumnException(
                    String.format("Column '%d' doesn't exist, '%d' is the largest column", columnIndex, WIDTH)
            );
        }
    }

    private void updateNextPlayer() {
        if (nextPlayer == player1) {
            nextPlayer = player2;
        } else {
            nextPlayer = player1;
        }
    }

    public boolean isOver() {
        return (checkRowForWin() ||
                checkColumnForWin() ||
                checkDiagonalForWin());
    }

    private boolean checkDiagonalForWin() {
        List<Coordinate[]> runsToCheck = new ArrayList<>();
        for (int candidatePositionIndex = 0; candidatePositionIndex < RUN_LENGTH; candidatePositionIndex++) {
            Coordinate[] runToCheck = generateAscendingDiagonalRunToCheck(candidatePositionIndex);
            if (runToCheck != null) {
                runsToCheck.add(runToCheck);
            }
            runToCheck = generateDescendingDiagonalRunToCheck(candidatePositionIndex);
            if (runToCheck != null) {
                runsToCheck.add(runToCheck);
            }
        }
        return checkRunsForWin(runsToCheck);
    }

    private Coordinate[] generateDescendingDiagonalRunToCheck(int candidatePositionIndex) {
        int startOfRunColumnIndex = lastColumnUsed - candidatePositionIndex;
        int startOfRunRowIndex = lastRowUsed - candidatePositionIndex;
        if (startOfRunRowIndex - (RUN_LENGTH - 1) < 0 || startOfRunRowIndex >= HEIGHT ||
                startOfRunColumnIndex < 0 || startOfRunColumnIndex + (RUN_LENGTH - 1) >= WIDTH
                ) {
            return null;
        }
        Coordinate[] runToCheck = new Coordinate[RUN_LENGTH];
        for (int offset = 0; offset < RUN_LENGTH; offset++) {
            runToCheck[offset] = new Coordinate(startOfRunColumnIndex + offset, startOfRunRowIndex - offset);
        }
        return runToCheck;
    }

    private Coordinate[] generateAscendingDiagonalRunToCheck(int candidatePositionIndex) {
        int startOfRunColumnIndex = lastColumnUsed - candidatePositionIndex;
        int startOfRunRowIndex = lastRowUsed - candidatePositionIndex;
        if (startOfRunRowIndex < 0 || startOfRunRowIndex + (RUN_LENGTH - 1) >= HEIGHT ||
            startOfRunColumnIndex < 0 || startOfRunColumnIndex + (RUN_LENGTH - 1) >= WIDTH
        ) {
            return null;
        }
        Coordinate[] runToCheck = new Coordinate[RUN_LENGTH];
        for (int offset = 0; offset < RUN_LENGTH; offset++) {
            runToCheck[offset] = new Coordinate(startOfRunColumnIndex + offset, startOfRunRowIndex + offset);
        }
        return runToCheck;
    }

    private boolean checkColumnForWin() {
        if (lastRowUsed < RUN_LENGTH - 1) {
            return false;
        }
        Coordinate[] runToCheck = new Coordinate[RUN_LENGTH];
        for (int rowOffset = 0; rowOffset < RUN_LENGTH; rowOffset++) {
            runToCheck[rowOffset] = new Coordinate(lastColumnUsed, lastRowUsed - rowOffset);
        }
        return checkRunForWin(runToCheck);
    }

    private boolean checkRowForWin() {
        List<Coordinate[]> runsToCheck = new ArrayList<>();
        for (int initialColumnIndex = 0; initialColumnIndex < 2; initialColumnIndex++) {
            Coordinate[] runToCheck = new Coordinate[RUN_LENGTH];
            for (int columnOffset = 0; columnOffset < RUN_LENGTH; columnOffset++) {
                runToCheck[columnOffset] = new Coordinate(initialColumnIndex + columnOffset, lastRowUsed);
            }
            runsToCheck.add(runToCheck);
        }
        return checkRunsForWin(runsToCheck);
    }

    /**
     * @param runsToCheck, a list of arrays of length RUN_LENGTH that specify the indices of the cells to check.
     * @return
     */
    private boolean checkRunsForWin(List<Coordinate[]> runsToCheck) {
        for (Coordinate[] runToCheck : runsToCheck) {
            if (checkRunForWin(runToCheck)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkRunForWin(Coordinate[] cells) {
        assert cells.length == RUN_LENGTH;
        boolean same = true;
        for (int i = 0; i < RUN_LENGTH - 1; i++) {
            ColouredDisc firstCell = getValueInCell(cells[i]);
            ColouredDisc secondCell = getValueInCell(cells[i + 1]);
            if (firstCell == null || secondCell == null) {
                return false;
            }
            same &= firstCell.equals(secondCell);
        }
        return same;
    }

    private ColouredDisc getValueInCell(Coordinate cell) {
        return grid[cell.getColumnIndex()][cell.getRowIndex()];
    }
}
