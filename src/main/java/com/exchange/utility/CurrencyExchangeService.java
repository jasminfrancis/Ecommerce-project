package com.exchange.utility;


import com.exchange.customException.ExchangeRateException;
import com.exchange.response.CurrencyApiResponse;
import com.exchange.service.BillingServiceImpl;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
@Data
public class CurrencyExchangeService {
    private static final Logger logger = LoggerFactory.getLogger(CurrencyExchangeService.class);
    @Value("${currency.api.key}")
    private String apiKey;
    private final String apiUrl ="https://open.er-api.com/v6/latest/{baseCurrency}?apikey={apiKey}";
    public BigDecimal getExchangeRate(String baseCurrency, String targetCurrency) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl.replace("{baseCurrency}", baseCurrency).replace("{apiKey}", apiKey);
        CurrencyApiResponse response = restTemplate.getForObject(url, CurrencyApiResponse.class);
        logger.info("Get currency rates: " + response.getRates());
        if (response != null && response.getRates().containsKey(targetCurrency)) {
            return BigDecimal.valueOf(response.getRates().get(targetCurrency));
        }else{
            logger.error("Error fetching exchange rate");
            throw new ExchangeRateException("Error fetching exchange rate");
        }

    }
}
