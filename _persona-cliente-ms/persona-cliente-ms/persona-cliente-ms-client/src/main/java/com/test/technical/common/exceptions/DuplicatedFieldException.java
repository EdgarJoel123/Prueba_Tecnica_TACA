package com.test.technical.common.exceptions;

public class DuplicatedFieldException extends RuntimeException {

    private final int errorCode;

    public DuplicatedFieldException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
