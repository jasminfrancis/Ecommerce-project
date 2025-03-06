package com.exchange.service;

import com.exchange.Model.Bill;
import com.exchange.constants.ResponseMessage;
import com.exchange.response.APIResponse;
import com.exchange.utility.CurrencyExchangeService;
import com.exchange.utility.DiscountService;
import java.math.BigDecimal;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BillingServiceImpl implements BillingService {
    private final CurrencyExchangeService currencyExchangeService;
    private final DiscountService discountService;

    public BillingServiceImpl(CurrencyExchangeService currencyExchangeService, DiscountService discountService) {
        this.currencyExchangeService = currencyExchangeService;
        this.discountService = discountService;
    }
    @Override
    public APIResponse<?> calculate(Bill bill) {
        // Get the discounted total amount
        BigDecimal discountedAmount = discountService.calculateDiscount(bill);
        // Get the exchange rate for currency conversion
        BigDecimal exchangeRate = currencyExchangeService.getExchangeRate(bill.getOriginalCurrency(), bill.getTargetCurrency());

        System.out.println("exchangeRatse=s==>"+exchangeRate);
        System.out.println("discountedAmount=>"+discountedAmount);
        BigDecimal payableAmount =discountedAmount.multiply(exchangeRate) ;
        System.out.println("payableAmount=>"+payableAmount);
        return new APIResponse<>(ResponseMessage.DATA_RETRIEVED_SUCCES,ResponseMessage.DATA_RETRIEVED_SUCCES,payableAmount, HttpStatus.OK.value());
    }
}
