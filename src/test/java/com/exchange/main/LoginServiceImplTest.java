package com.exchange.main;

import com.exchange.Model.LoginRequest;
import com.exchange.constants.ResponseMessage;
import com.exchange.response.APIResponse;
import com.exchange.security.JwtUtil;
import com.exchange.service.LoginServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class LoginServiceImplTest {
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private LoginServiceImpl loginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin_Success() {
        // Mock login request
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("jasmin");
        loginRequest.setPassword("Jasmin@123");

        // Mock authentication
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken("jasmin", "Jasmin@123"));

        // Mock token generation
        when(jwtUtil.generateToken("jasmin")).thenReturn("mocked-jwt-token");

        // Call method
        APIResponse<?> response = loginService.login(loginRequest);

        // Assertions
        assertEquals(HttpStatus.OK.value(), response.getHttpCode());
        assertEquals("mocked-jwt-token", response.getResponse());
        assertEquals(ResponseMessage.LOGIN_SUCESS, response.getMessage());

        // Verify interactions
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtUtil, times(1)).generateToken("jasmin");
    }

    @Test
    void testLogin_Failure() {
        // Mock login request
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("wronguser");
        loginRequest.setPassword("wrongpassword");

        // Mock authentication failure
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        // Call method & expect exception
        Exception exception = assertThrows(Exception.class, () -> loginService.login(loginRequest));

        // Verify interactions
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtUtil, times(0)).generateToken(anyString());

        // Assert exception message
        assertEquals("Invalid credentials", exception.getMessage());
    }
}
