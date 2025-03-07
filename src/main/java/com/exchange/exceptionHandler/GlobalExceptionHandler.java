package com.exchange.exceptionHandler;

import com.exchange.customException.ExchangeRateException;
import com.exchange.customException.LoginException;
import com.exchange.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public  class GlobalExceptionHandler {

    @ExceptionHandler(ExchangeRateException .class)
    public ResponseEntity<ErrorResponse> exchangeRateException(ExchangeRateException  ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage(), "error", "EXCHANGE_RATE_EXCEPTION", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ErrorResponse> loginException(LoginException  ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage(), "error", "LOGIN_FAILED",HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
