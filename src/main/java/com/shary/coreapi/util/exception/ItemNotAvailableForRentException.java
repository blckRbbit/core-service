package com.shary.coreapi.util.exception;

public class ItemNotAvailableForRentException extends RuntimeException {
    public ItemNotAvailableForRentException(String message) {
        super(message);
    }
}
