package com.exchange.utility;
import com.exchange.Model.Bill;
import com.exchange.Model.Item;
import com.exchange.Model.User;
import com.exchange.service.BillingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
@Service
public class DiscountService {
    private static final Logger logger = LoggerFactory.getLogger(DiscountService.class);
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
        // Apply percentage discount to non-grocery items
        BigDecimal nonGroceryAmount = totalAmount.subtract(groceryAmount);
        BigDecimal percentageDiscountAmount = nonGroceryAmount.multiply(percentageDiscount);
        // Apply flat discount ($5 for every $100)
        BigDecimal flatDiscount = totalAmount.divide(BigDecimal.valueOf(100), 0, RoundingMode.DOWN)
                .multiply(BigDecimal.valueOf(5));
        // Calculate final amount
        BigDecimal finalAmount = totalAmount.subtract(percentageDiscountAmount).subtract(flatDiscount);

         logger.info("Final Amount: " + finalAmount);

        return finalAmount;
    }
}
