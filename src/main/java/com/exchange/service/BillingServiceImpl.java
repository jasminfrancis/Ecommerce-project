package com.exchange.service;
import com.exchange.Model.Bill;
import com.exchange.constants.ResponseMessage;
import com.exchange.response.APIResponse;
import com.exchange.utility.CurrencyExchangeService;
import com.exchange.utility.DiscountService;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceImpl implements BillingService {
    private static final Logger logger = LoggerFactory.getLogger(BillingServiceImpl.class);
    private final CurrencyExchangeService currencyExchangeService;
    private final DiscountService discountService;
    public BillingServiceImpl(CurrencyExchangeService currencyExchangeService, DiscountService discountService) {
        this.currencyExchangeService = currencyExchangeService;
        this.discountService = discountService;
    }
    @Override
    public APIResponse<?> calculate(Bill bill) {
        logger.info("Calculating payable amount for bill: {}", bill);
        // Get the discounted total amount
        BigDecimal discountedAmount = discountService.calculateDiscount(bill);
        // Get the exchange rate for currency conversion
        BigDecimal exchangeRate = currencyExchangeService.getExchangeRate(bill.getOriginalCurrency(), bill.getTargetCurrency());
        BigDecimal payableAmount =discountedAmount.multiply(exchangeRate) ;
        logger.info("Final Payable Amount: {}", payableAmount);
        return new APIResponse<>(ResponseMessage.DATA_RETRIEVED_SUCCES,ResponseMessage.DATA_RETRIEVED_SUCCES,payableAmount, HttpStatus.OK.value());
    }
}
