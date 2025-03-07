package com.exchange.main;


import static org.junit.jupiter.api.Assertions.*;

import com.exchange.response.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ErrorResponseTest {

    private ErrorResponse errorResponse;

    @BeforeEach
    public void setup() {
        // Initialize ErrorResponse object
        errorResponse = new ErrorResponse("Error occurred", "error", "ERR_001", 400);
    }

    @Test
    public void testConstructor() {
        // Test if constructor correctly initializes all fields
        assertNotNull(errorResponse);
        assertEquals("Error occurred", errorResponse.getMessage());
        assertEquals("error", errorResponse.getStatus());
        assertEquals("ERR_001", errorResponse.getErrorCode());
        assertEquals(400, errorResponse.getHttpCode());
    }

    @Test
    public void testGetErrorCode() {
        // Test if getter for errorCode returns correct value
        assertEquals("ERR_001", errorResponse.getErrorCode());
    }

    @Test
    public void testSetErrorCode() {
        // Test if setter for errorCode works correctly
        errorResponse.setErrorCode("ERR_002");
        assertEquals("ERR_002", errorResponse.getErrorCode());
    }

    @Test
    public void testGetMessage() {
        // Test if getter for message returns correct value
        assertEquals("Error occurred", errorResponse.getMessage());
    }

    @Test
    public void testSetMessage() {
        // Test if setter for message works correctly
        errorResponse.setMessage("New error message");
        assertEquals("New error message", errorResponse.getMessage());
    }

    @Test
    public void testGetStatus() {
        // Test if getter for status returns correct value
        assertEquals("error", errorResponse.getStatus());
    }

    @Test
    public void testSetStatus() {
        // Test if setter for status works correctly
        errorResponse.setStatus("failed");
        assertEquals("failed", errorResponse.getStatus());
    }

    @Test
    public void testGetHttpCode() {
        // Test if getter for httpCode returns correct value
        assertEquals(400, errorResponse.getHttpCode());
    }

    @Test
    public void testSetHttpCode() {
        // Test if setter for httpCode works correctly
        errorResponse.setHttpCode(404);
        assertEquals(404, errorResponse.getHttpCode());
    }
}
