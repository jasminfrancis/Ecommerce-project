package com.exchange.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Item {
    private String name;
    private BigDecimal price;
    @JsonProperty("grocery")
    private boolean grocery;


}
