package com.exchange.main;


import com.exchange.security.JwtRequestFilter;
import com.exchange.security.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.lang.reflect.Method;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtRequestFilterTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private JwtRequestFilter jwtRequestFilter;

    private final String token = "mockedJwtToken";
    private final String username = "testUser";

    @BeforeEach
    void setup() {
        // No common stubs here. Stubs will be set up in individual test cases.
    }

    @Test
    void testDoFilterInternal_WithValidToken() throws Exception {
        // Setup: mock the request to return the Authorization header
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtUtil.extractUsername(token)).thenReturn(username);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtUtil.validateToken(token, userDetails)).thenReturn(true);

        // Use reflection to access the protected method
        Method doFilterInternal = JwtRequestFilter.class.getDeclaredMethod(
                "doFilterInternal", HttpServletRequest.class, HttpServletResponse.class, FilterChain.class);
        doFilterInternal.setAccessible(true); // Make method accessible

        // Invoke the method with mocked parameters
        doFilterInternal.invoke(jwtRequestFilter, request, response, filterChain);

        // Verify the request was processed through the filter
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    void testDoFilterInternal_WithInvalidToken() throws Exception {
        // Setup: mock the request to return the Authorization header
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtUtil.extractUsername(token)).thenReturn(username);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtUtil.validateToken(token, userDetails)).thenReturn(false);

        Method doFilterInternal = JwtRequestFilter.class.getDeclaredMethod(
                "doFilterInternal", HttpServletRequest.class, HttpServletResponse.class, FilterChain.class);
        doFilterInternal.setAccessible(true);

        doFilterInternal.invoke(jwtRequestFilter, request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        verifyNoMoreInteractions(userDetailsService);
    }

    @Test
    void testDoFilterInternal_NoAuthorizationHeader() throws Exception {
        // Setup: mock the request to return null for Authorization header
        when(request.getHeader("Authorization")).thenReturn(null);

        // Use reflection to access the protected method
        Method doFilterInternal = JwtRequestFilter.class.getDeclaredMethod(
                "doFilterInternal", HttpServletRequest.class, HttpServletResponse.class, FilterChain.class);
        doFilterInternal.setAccessible(true);

        // Invoke the method with mocked parameters
        doFilterInternal.invoke(jwtRequestFilter, request, response, filterChain);

        // Verify that the filter chain is still called even without the Authorization header
        verify(filterChain, times(1)).doFilter(request, response);

        // Verify no interactions with JwtUtil or UserDetailsService as the header was missing
        verifyNoInteractions(jwtUtil, userDetailsService);
    }
}