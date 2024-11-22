package com.example.zara.domain.excepiton;


public class PriceApplicationException extends RuntimeException {

    public PriceApplicationException(String message) {
        super(message);
    }

    public PriceApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
