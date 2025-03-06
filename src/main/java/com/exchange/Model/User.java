package com.exchange.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {
    @JsonProperty("employee")
    private boolean employee;
    @JsonProperty("affiliate")
    private boolean affiliate;
    @JsonProperty("customerForOverTwoYears")
    private boolean customerForOverTwoYears;
}
