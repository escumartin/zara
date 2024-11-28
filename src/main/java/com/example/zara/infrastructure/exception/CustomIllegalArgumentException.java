package com.example.zara.infrastructure.exception;

public class CustomIllegalArgumentException extends RuntimeException {
    public CustomIllegalArgumentException(String message) {
        super(message);
    }

    public CustomIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
