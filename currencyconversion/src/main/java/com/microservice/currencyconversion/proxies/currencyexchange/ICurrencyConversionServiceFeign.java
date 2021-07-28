package com.microservice.currencyconversion.proxies.currencyexchange;

import com.microservice.currencyconversion.proxies.currencyexchange.request.CurrencyExchange;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient("CURRENCY-EXCHANGE-SERVICE")
public interface ICurrencyConversionServiceFeign {

    @CrossOrigin
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    ResponseEntity<CurrencyExchange> retrieveExchangeValue(@PathVariable("from") String from,
                                                           @PathVariable("to") String to)
            throws Exception;
}
