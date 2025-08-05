package com.test.technical.common.exceptions;

public class IdNotFoundException extends RuntimeException {

    private final int errorCode;

    public IdNotFoundException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
