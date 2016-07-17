package org.willprice.connect4.model;

public class AbstractConnect4Tests {
    protected static final ColouredDisc YELLOW = new ColouredDisc("Yellow");
    protected static final ColouredDisc RED = new ColouredDisc("Red");

    protected void takeTurns(Connect4 connect4, int numberOfTurns) throws ColumnFullException, NonExistentColumnException {
        for (int i = 0; i < numberOfTurns; i++) {
            connect4.takeTurn();
        }
    }
}
