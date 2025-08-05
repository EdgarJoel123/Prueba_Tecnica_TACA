package com.test.technical.common.exceptions;

public class NoPermissionsException extends RuntimeException {

    private final int errorCode;

    public NoPermissionsException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
