package com.jee.exceptions;

public class NegativeInputNumberException extends RuntimeException {

    public NegativeInputNumberException() {
        super();
    }

    public NegativeInputNumberException(String message) {
        super(message);
    }

}
