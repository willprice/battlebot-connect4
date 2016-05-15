package org.willprice.connect4;

public class CheckColumnForWin extends GridCheck {
	@Override
	public boolean check(Grid grid) {
	    if (grid.getLastCoordinateUsed().getRowIndex() < grid.getRunLength() - 1) {
	        return false;
	    }
	    Coordinate[] runToCheck = new Coordinate[grid.getRunLength()];
	    for (int rowOffset = 0; rowOffset < grid.getRunLength(); rowOffset++) {
	        runToCheck[rowOffset] = new Coordinate(grid.getLastCoordinateUsed().getColumnIndex(), grid.getLastCoordinateUsed().getRowIndex() - rowOffset);
	    }
	    return checkRunForWin(grid, runToCheck);
	}
}
