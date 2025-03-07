package com.exchange.utility;


import com.exchange.customException.ExchangeRateException;
import com.exchange.response.CurrencyApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class CurrencyExchangeService {

    @Value("${currency.api.key}")
    private String apiKey;
    @Value("${currency.api.baseUrl}")
    private String baseUrl;


    private final String apiUrl ="https://open.er-api.com/v6/latest/{baseCurrency}?apikey={apiKey}";

    public BigDecimal getExchangeRate(String baseCurrency, String targetCurrency) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl.replace("{baseCurrency}", baseCurrency).replace("{apiKey}", apiKey);
        CurrencyApiResponse response = restTemplate.getForObject(url, CurrencyApiResponse.class);
       // System.out.println("Response Object: " + response);
        //System.out.println("Response Object: " + response.getRates());

        if (response != null && response.getRates().containsKey(targetCurrency)) {
            return BigDecimal.valueOf(response.getRates().get(targetCurrency));
        }
        throw new ExchangeRateException("Error fetching exchange rate");
    }
}
