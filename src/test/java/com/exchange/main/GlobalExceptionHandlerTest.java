package com.exchange.main;


import com.exchange.customException.ExchangeRateException;
import com.exchange.customException.LoginException;
import com.exchange.exceptionHandler.GlobalExceptionHandler;
import com.exchange.response.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setup() {
        globalExceptionHandler = new GlobalExceptionHandler();  // instantiate the exception handler
    }

    @Test
    public void testExchangeRateException() {
        // Arrange
        ExchangeRateException exception = new ExchangeRateException("Exchange rate exception occurred");

        // Act
        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.exchangeRateException(exception);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        ErrorResponse body = responseEntity.getBody();
        assert body != null;
        assertEquals("error", body.getStatus());
        assertEquals("EXCHANGE_RATE_EXCEPTION", body.getErrorCode());
        assertEquals("Exchange rate exception occurred", body.getMessage());
    }

    @Test
    public void testLoginException() {
        // Arrange
        LoginException exception = new LoginException("Login failed due to invalid credentials");

        // Act
        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.loginException(exception);

        // Assert
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
        ErrorResponse body = responseEntity.getBody();
        assert body != null;
        assertEquals("error", body.getStatus());
        assertEquals("LOGIN_FAILED", body.getErrorCode());
        assertEquals("Login failed due to invalid credentials", body.getMessage());
    }
}
