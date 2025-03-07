package com.exchange.controller;


import com.exchange.Model.Bill;
import com.exchange.response.APIResponse;
import com.exchange.service.BillingService;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BillingController {
    private static final Logger logger = LoggerFactory.getLogger(BillingController.class);
   @Autowired
   BillingService billingService;

    @GetMapping("/test")
    public String sayHello() {
        return "Hello, World! testings";
    }

    /**
     * Calculates the discount for a given bill.
     *
     * @param bill The bill containing user details and items.
     * @return The discounted amount.
     */
    @PostMapping("/calculate")
    public ResponseEntity<APIResponse<?>> calculateBill(@RequestBody Bill bill) {
        // Call the service method to calculate the payable amount
        logger.info("************ Calculate Controller Invoked *****");
        return ResponseEntity.ok(billingService.calculate(bill));
    }
}
