package com.exchange.response;

import lombok.Data;

import java.util.Map;
@Data
public class CurrencyApiResponse {
    private Map<String, Double> rates;  // A map of target currencies and their exchange rates

}
