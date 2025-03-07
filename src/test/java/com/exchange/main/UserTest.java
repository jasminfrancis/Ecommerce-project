package com.exchange.main;
import com.exchange.Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        user = new User();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testEmployeeSetterAndGetter() {
        user.setEmployee(true);
        assertTrue(user.isEmployee());

        user.setEmployee(false);
        assertFalse(user.isEmployee());
    }

    @Test
    public void testAffiliateSetterAndGetter() {
        user.setAffiliate(true);
        assertTrue(user.isAffiliate());

        user.setAffiliate(false);
        assertFalse(user.isAffiliate());
    }

    @Test
    public void testCustomerForOverTwoYearsSetterAndGetter() {
        user.setCustomerForOverTwoYears(true);
        assertTrue(user.isCustomerForOverTwoYears());

        user.setCustomerForOverTwoYears(false);
        assertFalse(user.isCustomerForOverTwoYears());
    }

    @Test
    public void testSerialization() throws Exception {
        user.setEmployee(true);
        user.setAffiliate(false);
        user.setCustomerForOverTwoYears(true);

        String json = objectMapper.writeValueAsString(user);
        assertTrue(json.contains("\"employee\":true"));
        assertTrue(json.contains("\"affiliate\":false"));
        assertTrue(json.contains("\"customerForOverTwoYears\":true"));
    }

    @Test
    public void testDeserialization() throws Exception {
        String json = "{\"employee\":true,\"affiliate\":false,\"customerForOverTwoYears\":true}";
        User deserializedUser = objectMapper.readValue(json, User.class);

        assertTrue(deserializedUser.isEmployee());
        assertFalse(deserializedUser.isAffiliate());
        assertTrue(deserializedUser.isCustomerForOverTwoYears());
    }
}
