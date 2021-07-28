package com.microservice.currencyconversion.proxies.currencyexchange.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("currency-exchange-base-path")
public class CurrencyExchangeConfig {

    private String url;
}
