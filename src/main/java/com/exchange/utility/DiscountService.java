package com.exchange.utility;

import com.exchange.Model.Bill;
import com.exchange.Model.Item;
import com.exchange.Model.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DiscountService {
    public BigDecimal calculateDiscount(Bill bill) {
        User user = bill.getUser();
        List<Item> items = bill.getItems();

        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal groceryAmount = BigDecimal.ZERO;

        for (Item item : items) {
            totalAmount = totalAmount.add(item.getPrice());
            if (item.isGrocery()) {
                groceryAmount = groceryAmount.add(item.getPrice());
            }
        }

        BigDecimal percentageDiscount = BigDecimal.ZERO;
        if (user.isEmployee()) {
            percentageDiscount = BigDecimal.valueOf(0.30);
        } else if (user.isAffiliate()) {
            percentageDiscount = BigDecimal.valueOf(0.10);
        } else if (user.isCustomerForOverTwoYears()) {
            percentageDiscount = BigDecimal.valueOf(0.05);
        }

        BigDecimal percentageDiscountAmount = totalAmount.subtract(groceryAmount).multiply(percentageDiscount);
        System.out.println("discountAmount==>"+percentageDiscountAmount);
        BigDecimal flatDiscount = BigDecimal.valueOf((totalAmount.intValue() / 100) * 5);
        System.out.println("flatDiscount==>"+flatDiscount);
        BigDecimal finalAmount = totalAmount.subtract(percentageDiscountAmount).subtract(flatDiscount);
        System.out.println("finalAmount==>"+finalAmount);

        return finalAmount;
    }
}
