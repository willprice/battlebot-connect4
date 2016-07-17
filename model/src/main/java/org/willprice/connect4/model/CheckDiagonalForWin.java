package org.willprice.connect4;

import org.willprice.connect4.model.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class CheckDiagonalForWin extends GridCheck {
	@Override
	public boolean check(Grid grid) {
	    List<Coordinate[]> runsToCheck = new ArrayList<>();
	    for (int candidatePositionIndex = 0; candidatePositionIndex < grid.getRunLength(); candidatePositionIndex++) {
	        Coordinate[] runToCheck = generateAscendingDiagonalRunToCheck(grid, candidatePositionIndex);
	        if (runToCheck != null) {
	            runsToCheck.add(runToCheck);
	        }
	        runToCheck = generateDescendingDiagonalRunToCheck(grid, candidatePositionIndex);
	        if (runToCheck != null) {
	            runsToCheck.add(runToCheck);
	        }
	    }
	    return checkRunsForWin(grid, runsToCheck);
	}

    private Coordinate[] generateDescendingDiagonalRunToCheck(Grid grid, int candidatePositionIndex) {
        int startOfRunColumnIndex = grid.getLastCoordinateUsed().getColumnIndex() - candidatePositionIndex;
        int startOfRunRowIndex = grid.getLastCoordinateUsed().getRowIndex() - candidatePositionIndex;
        if (startOfRunRowIndex - (grid.getRunLength() - 1) < 0 || startOfRunRowIndex >= grid.getRowsLength() ||
                startOfRunColumnIndex < 0 || startOfRunColumnIndex + (grid.getRunLength() - 1) >= grid.getColsLength()
                ) {
            return null;
        }
        Coordinate[] runToCheck = new Coordinate[grid.getRunLength()];
        for (int offset = 0; offset < grid.getRunLength(); offset++) {
            runToCheck[offset] = new Coordinate(startOfRunColumnIndex + offset, startOfRunRowIndex - offset);
        }
        return runToCheck;
    }

    private Coordinate[] generateAscendingDiagonalRunToCheck(Grid grid, int candidatePositionIndex) {
        int startOfRunColumnIndex = grid.getLastCoordinateUsed().getColumnIndex() - candidatePositionIndex;
        int startOfRunRowIndex = grid.getLastCoordinateUsed().getRowIndex() - candidatePositionIndex;
        if (startOfRunRowIndex < 0 || startOfRunRowIndex + (grid.getRunLength() - 1) >= grid.getRowsLength() ||
            startOfRunColumnIndex < 0 || startOfRunColumnIndex + (grid.getRunLength() - 1) >= grid.getColsLength()
        ) {
            return null;
        }
        Coordinate[] runToCheck = new Coordinate[grid.getRunLength()];
        for (int offset = 0; offset < grid.getRunLength(); offset++) {
            runToCheck[offset] = new Coordinate(startOfRunColumnIndex + offset, startOfRunRowIndex + offset);
        }
        return runToCheck;
    }
}
