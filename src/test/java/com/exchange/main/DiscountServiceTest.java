package com.exchange.main;

import com.exchange.Model.Bill;
import com.exchange.Model.Item;
import com.exchange.Model.User;
import com.exchange.utility.DiscountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class DiscountServiceTest {

    @InjectMocks
    private DiscountService discountService;

    private User employeeUser;
    private User affiliateUser;
    private User longTermCustomerUser;




    @BeforeEach
    void setUp() {
        // Create users with different discount eligibility
        employeeUser = new User();
        employeeUser.setEmployee(true);

        affiliateUser = new User();
        affiliateUser.setAffiliate(true);

        longTermCustomerUser = new User();
        longTermCustomerUser.setCustomerForOverTwoYears(true);


    }

    // Test case for employee discount (e.g., 20% off on all items)
    @Test
    void testCalculateDiscount_Employee() {
        // Arrange
        Item groceryItem = new Item();
        groceryItem.setName("Milk");
        groceryItem.setPrice(BigDecimal.valueOf(50));
        groceryItem.setGrocery(true);

        Item nonGroceryItem = new Item();
        nonGroceryItem.setName("TV");
        nonGroceryItem.setPrice(BigDecimal.valueOf(200));
        nonGroceryItem.setGrocery(false);

        List<Item> items = Arrays.asList(groceryItem, nonGroceryItem);

        Bill bill = new Bill();
        bill.setUser(employeeUser);
        bill.setItems(items);

        // Act
        BigDecimal finalAmount = discountService.calculateDiscount(bill);

        // Debugging: Print the final amount for employee
        System.out.println("Employee Test - Final Amount: " + finalAmount);

        // Assert: Assuming a 20% discount for employees
        BigDecimal expectedAmount = BigDecimal.valueOf(180); // 20% off of 50 + 200
        assertEquals(expectedAmount.setScale(2, RoundingMode.HALF_UP), finalAmount.setScale(2, RoundingMode.HALF_UP),
                "The employee discount calculation is incorrect.");
    }

    // Test case for affiliate discount (e.g., 10% off non-grocery items)
    @Test
    void testCalculateDiscount_Affiliate() {
        // Arrange
        Item groceryItem = new Item();
        groceryItem.setName("Milk");
        groceryItem.setPrice(BigDecimal.valueOf(50));
        groceryItem.setGrocery(true);

        Item nonGroceryItem = new Item();
        nonGroceryItem.setName("TV");
        nonGroceryItem.setPrice(BigDecimal.valueOf(200));
        nonGroceryItem.setGrocery(false);

        List<Item> items = Arrays.asList(groceryItem, nonGroceryItem);

        Bill bill = new Bill();
        bill.setUser(affiliateUser);
        bill.setItems(items);

        // Act
        BigDecimal finalAmount = discountService.calculateDiscount(bill);

        // Debugging: Print the final amount for affiliate
        System.out.println("Affiliate Test - Final Amount: " + finalAmount);

        // Assert: Assuming a 10% discount for affiliates on non-grocery items
        BigDecimal expectedAmount = BigDecimal.valueOf(220); // 50 + 200 - 10% off of 200
        assertEquals(expectedAmount.setScale(2, RoundingMode.HALF_UP), finalAmount.setScale(2, RoundingMode.HALF_UP),
                "The affiliate discount calculation is incorrect.");
    }

    // Test case for long-term customer discount (e.g., 10% off for customers over two years)
    @Test
    void testCalculateDiscount_LongTermCustomer() {
        // Arrange
        Item groceryItem = new Item();
        groceryItem.setName("Milk");
        groceryItem.setPrice(BigDecimal.valueOf(50));
        groceryItem.setGrocery(true);

        Item nonGroceryItem = new Item();
        nonGroceryItem.setName("TV");
        nonGroceryItem.setPrice(BigDecimal.valueOf(200));
        nonGroceryItem.setGrocery(false);

        List<Item> items = Arrays.asList(groceryItem, nonGroceryItem);

        Bill bill = new Bill();
        bill.setUser(longTermCustomerUser);
        bill.setItems(items);

        // Act
        BigDecimal finalAmount = discountService.calculateDiscount(bill);

        // Debugging: Print the final amount for long-term customer
        System.out.println("Long-Term Customer Test - Final Amount: " + finalAmount);

        // Assert: Assuming a 10% discount for long-term customers
        BigDecimal expectedAmount = BigDecimal.valueOf(230); // 50 + 200 - 10% off of 200
        assertEquals(expectedAmount.setScale(2, RoundingMode.HALF_UP), finalAmount.setScale(2, RoundingMode.HALF_UP),
                "The long-term customer discount calculation is incorrect.");
    }

    // Test case for regular customer with no discounts

}
