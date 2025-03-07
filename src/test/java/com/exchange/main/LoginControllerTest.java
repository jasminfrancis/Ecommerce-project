package com.exchange.main;
import com.exchange.Model.LoginRequest;
import com.exchange.controller.LoginController;
import com.exchange.response.APIResponse;
import com.exchange.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginControllerTest {

    @Mock
    private LoginService loginService; // Mock the service layer

    @InjectMocks
    private LoginController loginController; // Inject the mock into the controller

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize the mocks
    }

    @Test
    void testLogin_Success() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("password123");

        APIResponse<?> mockResponse = new APIResponse<>("Login successful", "success", "TOKEN_ABC123", 200);

        // Use ArgumentMatchers to avoid exact object matching issue
       // when(loginService.login(any(LoginRequest.class))).thenReturn(mockResponse);
        when(loginService.login(any(LoginRequest.class))).thenReturn((APIResponse) mockResponse);

        // Act
        ResponseEntity<APIResponse<?>> response = loginController.login(loginRequest);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Login successful", response.getBody().getMessage());
        assertEquals("success", response.getBody().getStatus());
        assertEquals("TOKEN_ABC123", response.getBody().getResponse());
    }
}




