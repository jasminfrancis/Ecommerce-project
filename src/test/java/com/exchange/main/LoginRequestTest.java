package com.exchange.main;
import com.exchange.Model.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class LoginRequestTest {
    private LoginRequest loginRequest;

    @BeforeEach
    public void setUp() {
        loginRequest = new LoginRequest();
    }

    @Test
    public void testUsernameSetterAndGetter() {
        loginRequest.setUsername("testUser");
        assertEquals("testUser", loginRequest.getUsername());
    }

    @Test
    public void testPasswordSetterAndGetter() {
        loginRequest.setPassword("testPassword");
        assertEquals("testPassword", loginRequest.getPassword());
    }

    @Test
    public void testEqualsAndHashCode() {
        LoginRequest loginRequest1 = new LoginRequest();
        loginRequest1.setUsername("testUser");
        loginRequest1.setPassword("testPassword");

        LoginRequest loginRequest2 = new LoginRequest();
        loginRequest2.setUsername("testUser");
        loginRequest2.setPassword("testPassword");

        assertEquals(loginRequest1, loginRequest2);
        assertEquals(loginRequest1.hashCode(), loginRequest2.hashCode());
    }

    @Test
    public void testToString() {
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("testPassword");
        String expectedString = "LoginRequest(username=testUser, password=testPassword)";
        assertEquals(expectedString, loginRequest.toString());
    }
}
