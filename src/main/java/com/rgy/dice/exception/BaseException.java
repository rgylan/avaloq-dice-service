package com.rgy.dice.exception;

public abstract class BaseException extends RuntimeException {

    protected final String message;

    public BaseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
