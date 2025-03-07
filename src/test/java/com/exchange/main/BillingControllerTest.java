package com.exchange.main;
import com.exchange.Model.Bill;

import com.exchange.Model.Item;
import com.exchange.Model.User;
import com.exchange.controller.BillingController;
import com.exchange.response.APIResponse;
import com.exchange.service.BillingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BillingControllerTest {
    @InjectMocks
    private BillingController billingController;

    @Mock
    private BillingService billingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sayHello_ReturnsExpectedMessage() {
        String result = billingController.sayHello();
        assertEquals("Hello, World! testings", result);
    }

    @Test
    void calculateBill_ReturnsCorrectResponse() {
        // Arrange
        Bill bill = new Bill();
        User user = new User();
        user.setEmployee(true);
        bill.setUser(user);

        Item item = new Item();
        item.setName("Test Item");
        item.setPrice(new BigDecimal("100"));
        item.setGrocery(false);

        bill.setItems(Arrays.asList(item));
        bill.setOriginalCurrency("USD");
        bill.setTargetCurrency("EUR");

        APIResponse<?> mockResponse = new APIResponse<>("Success", "Bill calculated", new BigDecimal("85.00"), HttpStatus.OK.value());
        doReturn(mockResponse).when(billingService).calculate(any(Bill.class));

        // Act
        ResponseEntity<APIResponse<?>> response = billingController.calculateBill(bill);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        verify(billingService, times(1)).calculate(bill);
    }
}
