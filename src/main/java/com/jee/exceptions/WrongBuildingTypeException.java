package com.jee.exceptions;

public class WrongBuildingTypeException extends RuntimeException {
    public WrongBuildingTypeException() {
        super();
    }

    public WrongBuildingTypeException(String message) {
        super(message);
    }
}
