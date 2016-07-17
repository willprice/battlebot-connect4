package org.willprice.connect4.model;

public class Coordinate {
    private final int columnIndex;
    private final int rowIndex;

    public Coordinate(int columnIndex, int rowIndex) {
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }
}
