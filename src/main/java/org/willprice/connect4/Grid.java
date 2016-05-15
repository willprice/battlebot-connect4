package org.willprice.connect4;


public class Grid {
	private ColouredDisc[][] grid;
	private int cols;
	private int rows;
    private int runLength;
    private Coordinate lastCoordinateUsed;

	public Grid(int cols, int rows, int runLength) {
		this.cols = cols;
		this.rows = rows;
		this.runLength = runLength;
		this.grid = new ColouredDisc[cols][rows];
	}

	public int getRowsLength() {
		return rows;
	}

	public int getColsLength() {
		return cols;
	}

	public int getNumberOfTokens(int columnIndex) {
	    int numberOfTokens = 0;
	    while(numberOfTokens < rows && hasDisc(columnIndex, numberOfTokens)) {
	        numberOfTokens++;
	    }
	    return numberOfTokens;
	}

	public void insertDiscInColumn(ColouredDisc disc, int columnIndex) throws NonExistentColumnException, ColumnFullException {
		checkColumnExists(columnIndex);
	    int nextFreeRowIndex = getNumberOfTokens(columnIndex);
	    checkColumnIsntFull(nextFreeRowIndex);
	    setDisc(columnIndex, nextFreeRowIndex, disc);
        lastCoordinateUsed = new Coordinate(columnIndex, nextFreeRowIndex);
	}

	public boolean isOver() {
        CheckRowForWin checkRowForWin = new CheckRowForWin();
		CheckColumnForWin checkColumnForWin = new CheckColumnForWin();
		CheckDiagonalForWin checkDiagonalForWin = new CheckDiagonalForWin();
		return checkRowForWin.check(this) || checkColumnForWin.check(this) || checkDiagonalForWin.check(this);
    }

	public int getRunLength() {
		return runLength;
	}

	public Coordinate getLastCoordinateUsed() {
		return lastCoordinateUsed;
	}

    public ColouredDisc getDisc(Coordinate coordinate) {
        return getDisc(coordinate.getColumnIndex(), coordinate.getRowIndex());
    }

	private boolean hasDisc(int col, int row) {
		return grid[col][row] != null;
	}

	private void setDisc(int col, int row, ColouredDisc disc) {
		grid[col][row] = disc;
	}

	private ColouredDisc getDisc(int col, int row) {
		return grid[col][row];
	}

	private void checkColumnIsntFull(int rowIndex) throws ColumnFullException {
	    if (rowIndex >= rows) {
	        throw new ColumnFullException();
	    }
	}

	private void checkColumnExists(int columnIndex) throws NonExistentColumnException {
	    if (columnIndex >= cols) {
	        throw new NonExistentColumnException(
	                String.format("Column '%d' doesn't exist, '%d' is the largest column", columnIndex, cols)
	        );
	    }
	}
}