package com.test.technical.common.exceptions;

public class EmptyReportException extends RuntimeException {

    private final int errorCode;

    public EmptyReportException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
