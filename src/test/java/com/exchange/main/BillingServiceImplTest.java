package com.exchange.main;

import com.exchange.Model.Bill;
import com.exchange.Model.Item;
import com.exchange.Model.User;
import com.exchange.constants.ResponseMessage;
import com.exchange.response.APIResponse;
import com.exchange.service.BillingServiceImpl;
import com.exchange.utility.CurrencyExchangeService;
import com.exchange.utility.DiscountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;

public class BillingServiceImplTest {
    @Mock
    private CurrencyExchangeService currencyExchangeService;

    @Mock
    private DiscountService discountService;

    @InjectMocks
    private BillingServiceImpl billingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculate() {
//     User user = new User(true, false, false);
//        Bill bill = new Bill(user, Arrays.asList(
//                new Item("Shirt", BigDecimal.valueOf(200), false),
//                new Item("Apple", BigDecimal.valueOf(50), true),
//                new Item("Shoes", BigDecimal.valueOf(150), false)
//        ), "USD", "EUR");
        // Mocking user
        User user = new User();
        user.setEmployee(false);
        user.setAffiliate(true);
        user.setCustomerForOverTwoYears(false);

        // Mocking items
        Item item1 = new Item();
        item1.setName("Shirt");
        item1.setPrice(BigDecimal.valueOf(300));
        item1.setGrocery(false);

        Item item2 = new Item();
        item2.setName("Apple");
        item2.setPrice(BigDecimal.valueOf(170.5));
        item2.setGrocery(true);

        Item item3 = new Item();
        item3.setName("Shoes");
        item3.setPrice(BigDecimal.valueOf(120));
        item3.setGrocery(false);

        Bill bill = new Bill();
        bill.setUser(user);
        bill.setItems(Arrays.asList(item1, item2, item3));
        bill.setOriginalCurrency("USD");
        bill.setTargetCurrency("AED");

        // Mock the discount calculation
        when(discountService.calculateDiscount(bill)).thenReturn(BigDecimal.valueOf(500));

        // Mock the exchange rate
        when(currencyExchangeService.getExchangeRate("USD", "AED")).thenReturn(BigDecimal.valueOf(3.67));

        // Call the method
        APIResponse<?> response = billingService.calculate(bill);


        // Assertions
        assertEquals(HttpStatus.OK.value(), response.getHttpCode());
        assertEquals(new BigDecimal("1835.00"), response.getResponse()); // 500 * 3.67

        // Verify interactions
        verify(discountService, times(1)).calculateDiscount(bill);
        verify(currencyExchangeService, times(1)).getExchangeRate("USD", "AED");
    }
}
