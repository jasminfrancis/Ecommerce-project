package com.exchange.customException;

public class LoginException extends RuntimeException {
    public LoginException(String message) {
        super(message);
    }
}
