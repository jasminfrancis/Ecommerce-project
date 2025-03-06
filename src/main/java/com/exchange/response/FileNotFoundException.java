package com.exchange.response;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String message) {
        super(message);
    }
}
