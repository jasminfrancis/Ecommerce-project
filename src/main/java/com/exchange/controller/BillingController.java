package com.exchange.controller;


import com.exchange.Model.Bill;
import com.exchange.response.APIResponse;
import com.exchange.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BillingController {

   @Autowired
   BillingService billingService;

    @GetMapping("/test")
    public String sayHello() {
        return "Hello, World! testings";
    }


    @PostMapping("/calculate")
    public ResponseEntity<APIResponse<?>> calculateBill(@RequestBody Bill bill) {
        // Call the service method to calculate the payable amount
        return ResponseEntity.ok(billingService.calculate(bill));
    }
}
