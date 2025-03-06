package com.exchange.customException;

public class ExchangeRateException extends RuntimeException {
    public ExchangeRateException(String message) {
        super(message);
    }
}
