package com.exchange.main;



import com.exchange.security.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;
    private String validToken;
    private String expiredToken;
    private final String username = "testUser";

    @Mock
    private UserDetails mockUserDetails;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        validToken = jwtUtil.generateToken(username);

        // Creating an expired token manually
        expiredToken = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis() - 1000 * 60 * 10)) // 10 minutes ago
                .setExpiration(new Date(System.currentTimeMillis() - 1000 * 60 * 5)) // Expired 5 minutes ago
                .signWith(SignatureAlgorithm.HS256, "mysamplesecretkeyforaccessingallendspointsusingjwtauthentication")
                .compact();
    }

    @Test
    void testGenerateToken() {
        assertNotNull(validToken);
        assertEquals(username, jwtUtil.extractUsername(validToken));
    }

    @Test
    void testExtractUsername() {
        assertEquals(username, jwtUtil.extractUsername(validToken));
    }

    @Test
    void testIsTokenExpired_False() {
        assertFalse(jwtUtil.isTokenExpired(validToken));
    }

    @Test
    void testIsTokenExpired_True() {
        assertThrows(ExpiredJwtException.class, () -> jwtUtil.isTokenExpired(expiredToken));
    }

    @Test
    void testValidateToken_Valid() {
        UserDetails userDetails = new User(username, "", new java.util.ArrayList<>());
        assertTrue(jwtUtil.validateToken(validToken, userDetails));
    }

    @Test
    void testValidateToken_Expired() {
        UserDetails userDetails = new User(username, "", new java.util.ArrayList<>());
        assertFalse(jwtUtil.validateToken(expiredToken, userDetails));
    }
}
