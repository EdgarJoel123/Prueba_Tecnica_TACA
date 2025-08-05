package com.test.technical.common.exceptions;

public class BadFieldException extends RuntimeException {

    private final int errorCode;

    public BadFieldException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
