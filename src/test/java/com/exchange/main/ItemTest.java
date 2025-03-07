package com.exchange.main;



import com.exchange.Model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ItemTest {

    private Item item;

    @BeforeEach
    void setUp() {
        item = new Item();
        item.setName("Laptop");
        item.setPrice(BigDecimal.valueOf(1200.50));
        item.setGrocery(false);
    }

    @Test
    void testItemFields() {
        assertThat(item.getName()).isEqualTo("Laptop");
        assertThat(item.getPrice()).isEqualTo(BigDecimal.valueOf(1200.50));
        assertThat(item.isGrocery()).isFalse();
    }

    @Test
    void testLombokGeneratedMethods() {
        Item anotherItem = new Item();
        anotherItem.setName("Laptop");
        anotherItem.setPrice(BigDecimal.valueOf(1200.50));
        anotherItem.setGrocery(false);

        assertThat(item).isEqualTo(anotherItem); // Tests Lombok's @Data equals() method
        assertThat(item.hashCode()).isEqualTo(anotherItem.hashCode());
    }
}
