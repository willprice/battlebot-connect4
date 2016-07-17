package org.willprice.connect4.model;

public class NonExistentColumnException extends Throwable {
    public NonExistentColumnException(String error) {
        super(error);
    }
}
