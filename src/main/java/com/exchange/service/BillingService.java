package com.exchange.service;

import com.exchange.Model.Bill;
import com.exchange.response.APIResponse;

public interface BillingService {
    APIResponse<?> calculate(Bill bill);
}
