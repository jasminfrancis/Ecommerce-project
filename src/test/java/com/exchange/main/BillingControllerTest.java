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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BillingControllerTest {

    @Mock
    private BillingService billingService; // Mock the BillingService dependency

    @InjectMocks
    private BillingController billingController; // Inject mocks into the controller

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testSayHello() {
        // Act
        String result = billingController.sayHello();

        // Assert
        assertEquals("Hello, World! testings", result);
    }

    @Test
    void testCalculateBill() {
        // Arrange
        // Create a User
        User user = new User();
        user.setEmployee(false);
        user.setAffiliate(true);
        user.setCustomerForOverTwoYears(true);

        // Create Items
        Item item1 = new Item();
        item1.setName("Laptop");
        item1.setPrice(new BigDecimal("1000.00"));
        item1.setGrocery(false);

        Item item2 = new Item();
        item2.setName("Milk");
        item2.setPrice(new BigDecimal("5.00"));
        item2.setGrocery(true);

        List<Item> items = Arrays.asList(item1, item2);

        // Create a Bill
        Bill bill = new Bill();
        bill.setUser(user);
        bill.setItems(items);
        bill.setOriginalCurrency("USD");
        bill.setTargetCurrency("EUR");

       //  Mock the service response
//        APIResponse<?> mockResponse = new APIResponse<>();
//        mockResponse.setStatus("SUCCESS");
//        mockResponse.setMessage("Bill calculated successfully");
//
//        // Explicitly specify the type for thenReturn
//        when(billingService.calculate(bill)).thenReturn(mockResponse); // Mock the service method



       APIResponse<?> mockResponse = new APIResponse<>("Success", "Bill calculated", new BigDecimal("85.00"), HttpStatus.OK.value());
        doReturn(mockResponse).when(billingService).calculate(any(Bill.class));
        // Act
        ResponseEntity<APIResponse<?>> response = billingController.calculateBill(bill);

        // Assert
//        assertEquals(200, response.getStatusCode().value());
//        assertEquals("SUCCESS", mockResponse.getStatus());
//        assertEquals("Bill calculated successfully",mockResponse.getMessage());

        verify(billingService, times(1)).calculate(bill); // Verify the service method was called once
    }
}