package com.shary.coreapi.util.exception;

public class EntityCouldNotBeCreatedException extends RuntimeException {
    public EntityCouldNotBeCreatedException() {
        super("Entity could not be created.");
    }

    public EntityCouldNotBeCreatedException(String message) {
        super(message);
    }
}
