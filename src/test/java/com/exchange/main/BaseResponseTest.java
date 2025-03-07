package com.exchange.main;



import static org.junit.jupiter.api.Assertions.*;

import com.exchange.response.BaseResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BaseResponseTest {

    private BaseResponse baseResponse;

    // Create a concrete subclass to instantiate the abstract BaseResponse class for testing
    private class ConcreteBaseResponse extends BaseResponse {
        public ConcreteBaseResponse(String message, String status, int httpCode) {
            super(message, status, httpCode);
        }
    }

    @BeforeEach
    public void setup() {
        // Initialize BaseResponse using the concrete subclass
        baseResponse = new ConcreteBaseResponse("Success", "success", 200);
    }

    @Test
    public void testConstructor() {
        // Test constructor initialization
        assertNotNull(baseResponse);
        assertEquals("Success", baseResponse.getMessage());
        assertEquals("success", baseResponse.getStatus());
        assertEquals(200, baseResponse.getHttpCode());
    }

    @Test
    public void testGetMessage() {
        // Test getter for message
        assertEquals("Success", baseResponse.getMessage());
    }

    @Test
    public void testSetMessage() {
        // Test setter for message
        baseResponse.setMessage("Updated Message");
        assertEquals("Updated Message", baseResponse.getMessage());
    }

    @Test
    public void testGetStatus() {
        // Test getter for status
        assertEquals("success", baseResponse.getStatus());
    }

    @Test
    public void testSetStatus() {
        // Test setter for status
        baseResponse.setStatus("failure");
        assertEquals("failure", baseResponse.getStatus());
    }

    @Test
    public void testGetHttpCode() {
        // Test getter for httpCode
        assertEquals(200, baseResponse.getHttpCode());
    }

    @Test
    public void testSetHttpCode() {
        // Test setter for httpCode
        baseResponse.setHttpCode(500);
        assertEquals(500, baseResponse.getHttpCode());
    }
}
