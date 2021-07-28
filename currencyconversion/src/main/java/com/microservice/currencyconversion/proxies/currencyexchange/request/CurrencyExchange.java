package com.microservice.currencyconversion.proxies.currencyexchange.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CurrencyExchange {

    private Long id;

    private String from;

    private String to;

    private BigDecimal conversionMultiple;

    private String environment;
}
