package org.willprice.connect4;

import java.util.List;

public abstract class GridCheck {
	public abstract boolean check(Grid grid);

	/**
	 * @param grid TODO
	 * @param runsToCheck TODO
	 * @param runsToCheck, a list of arrays of length RUN_LENGTH that specify the indices of the cells to check.
	 * @return
	 */
	boolean checkRunsForWin(Grid grid, List<Coordinate[]> runsToCheck) {
	    for (Coordinate[] runToCheck : runsToCheck) {
	        if (new CheckRowForWin().checkRunForWin(grid, runToCheck)) {
	            return true;
	        }
	    }
	    return false;
	}

	boolean checkRunForWin(Grid grid, Coordinate[] cells) {
	    assert cells.length == grid.getRunLength();
	    boolean same = true;
	    for (int i = 0; i < grid.getRunLength() - 1; i++) {
	        ColouredDisc firstCell = grid.getDisc(cells[i]);
	        ColouredDisc secondCell = grid.getDisc(cells[i + 1]);
	        if (firstCell == null || secondCell == null) {
	            return false;
	        }
	        same &= firstCell.equals(secondCell);
	    }
	    return same;
	}
}