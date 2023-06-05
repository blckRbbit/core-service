package com.shary.coreapi.util.exception;

public class UpdateRequestInsufficientRightsException extends RuntimeException {
    public UpdateRequestInsufficientRightsException(String message) {
        super(message);
    }
}
