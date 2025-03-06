package com.exchange.Model;

import lombok.Data;

import java.util.List;

@Data
public class Bill {
    private User user;
    private List<Item> items;
    private String originalCurrency;
    private String targetCurrency;
}
