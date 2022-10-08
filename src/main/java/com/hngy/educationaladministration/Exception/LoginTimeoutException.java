package com.hngy.educationaladministration.Exception;

public class LoginTimeoutException extends RuntimeException {
    public LoginTimeoutException() {
    }

    public LoginTimeoutException(String message) {
        super(message);
    }

    public LoginTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginTimeoutException(Throwable cause) {
        super(cause);
    }

    public LoginTimeoutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
