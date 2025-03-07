package com.exchange.main;


import static org.junit.jupiter.api.Assertions.*;

import com.exchange.response.APIResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class APIResponseTest {

    private APIResponse<String> apiResponse;

    @BeforeEach
    public void setup() {
        // Initialize APIResponse before each test
        apiResponse = new APIResponse<>("Success", "success", "Response Data", 200);
    }

    @Test
    public void testConstructorWithResponse() {
        // Test if constructor correctly initializes all fields
        assertNotNull(apiResponse);
        assertEquals("Success", apiResponse.getMessage());
        assertEquals("success", apiResponse.getStatus());
        assertEquals(200, apiResponse.getHttpCode());
        assertEquals("Response Data", apiResponse.getResponse());
    }

    @Test
    public void testConstructorWithoutResponse() {
        // Create a new APIResponse without the response parameter
        APIResponse<String> apiResponseNoResponse = new APIResponse<>("Error", "error", 400);

        // Test if constructor correctly initializes fields
        assertNotNull(apiResponseNoResponse);
        assertEquals("Error", apiResponseNoResponse.getMessage());
        assertEquals("error", apiResponseNoResponse.getStatus());
        assertEquals(400, apiResponseNoResponse.getHttpCode());
        assertNull(apiResponseNoResponse.getResponse()); // response should be null
    }

    @Test
    public void testSetResponse() {
        // Set a new response and validate
        apiResponse.setResponse("New Response Data");
        assertEquals("New Response Data", apiResponse.getResponse());
    }

    @Test
    public void testGetResponse() {
        // Validate that the response getter works correctly
        assertEquals("Response Data", apiResponse.getResponse());
    }
}
