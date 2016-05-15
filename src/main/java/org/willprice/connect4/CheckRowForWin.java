package org.willprice.connect4;

import java.util.ArrayList;
import java.util.List;

public class CheckRowForWin extends GridCheck {
	@Override
	public boolean check(Grid grid) {
	    List<Coordinate[]> runsToCheck = new ArrayList<>();
	    for (int initialColumnIndex = 0; initialColumnIndex < 2; initialColumnIndex++) {
	        Coordinate[] runToCheck = new Coordinate[grid.getRunLength()];
	        for (int columnOffset = 0; columnOffset < grid.getRunLength(); columnOffset++) {
	            runToCheck[columnOffset] = new Coordinate(initialColumnIndex + columnOffset, grid.getLastCoordinateUsed().getRowIndex());
	        }
	        runsToCheck.add(runToCheck);
	    }
	    return checkRunsForWin(grid, runsToCheck);
	}
}
