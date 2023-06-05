package com.shary.coreapi.util.exception;

public class UnsupportedRentServiceException extends RuntimeException {
    public UnsupportedRentServiceException() {
        super("The user cannot rent an item that belongs to him");
    }

    public UnsupportedRentServiceException(String message) {
        super(message);
    }
}
