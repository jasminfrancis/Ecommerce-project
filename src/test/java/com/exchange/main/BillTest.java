package com.exchange.main;
import com.exchange.Model.Bill;
import com.exchange.Model.Item;
import com.exchange.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BillTest {

    private Bill bill;
    private User user;
    private List<Item> items;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmployee(true);
        user.setAffiliate(false);
        user.setCustomerForOverTwoYears(true);

        Item item1 = new Item();
        item1.setName("Laptop");
        item1.setPrice(BigDecimal.valueOf(1000));
        item1.setGrocery(false);

        Item item2 = new Item();
        item2.setName("Apple");
        item2.setPrice(BigDecimal.valueOf(10));
        item2.setGrocery(true);

        items = Arrays.asList(item1, item2);

        bill = new Bill();
        bill.setUser(user);
        bill.setItems(items);
        bill.setOriginalCurrency("USD");
        bill.setTargetCurrency("AED");
    }

    @Test
    void testBillFields() {
        assertThat(bill.getUser()).isEqualTo(user);
        assertThat(bill.getItems()).isEqualTo(items);
        assertThat(bill.getOriginalCurrency()).isEqualTo("USD");
        assertThat(bill.getTargetCurrency()).isEqualTo("AED");
    }

    @Test
    void testLombokGeneratedMethods() {
        Bill anotherBill = new Bill();
        anotherBill.setUser(user);
        anotherBill.setItems(items);
        anotherBill.setOriginalCurrency("USD");
        anotherBill.setTargetCurrency("AED");

        assertThat(bill).isEqualTo(anotherBill); // Tests Lombok's @Data equals() method
        assertThat(bill.hashCode()).isEqualTo(anotherBill.hashCode());
    }
}
