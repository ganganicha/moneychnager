package com.moneyexchanger.exception;

public class CustomException extends RuntimeException {
    private final int errorCode;
    private final String message;

    public CustomException(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public CustomException(String message, int errorCode, String message1) {
        super(message);
        this.errorCode = errorCode;
        this.message = message1;
    }

    public CustomException(String message, Throwable cause, int errorCode, String message1) {
        super(message, cause);
        this.errorCode = errorCode;
        this.message = message1;
    }

    public CustomException(Throwable cause, int errorCode, String message) {
        super(cause);
        this.errorCode = errorCode;
        this.message = message;
    }

    public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errorCode, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.message = message1;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
