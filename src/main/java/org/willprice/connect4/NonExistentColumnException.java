package org.willprice.connect4;

public class NonExistentColumnException extends Throwable {
    public NonExistentColumnException(String error) {
        super(error);
    }
}
