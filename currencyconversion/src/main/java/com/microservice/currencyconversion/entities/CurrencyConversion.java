package com.microservice.currencyconversion.entities;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CurrencyConversion {

    private Long id;

    private String from;

    private String to;

    private BigDecimal conversionMultiple;

    private String environment;

    private BigDecimal quantity;

    private BigDecimal totalAmount;


}
