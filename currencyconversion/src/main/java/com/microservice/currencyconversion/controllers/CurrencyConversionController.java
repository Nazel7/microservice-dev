package com.microservice.currencyconversion.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.currencyconversion.proxies.currencyexchange.ICurrencyConversionServiceFeign;
import com.microservice.currencyconversion.entities.CurrencyConversion;
import com.microservice.currencyconversion.interfaces.ICurrencyConversionService;
import com.microservice.currencyconversion.proxies.currencyexchange.request.CurrencyExchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.MathContext;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@RequestMapping("/currency-conversion")
public class CurrencyConversionController {

    private final ICurrencyConversionServiceFeign mICurrencyConversionServiceFeign;

    private final ICurrencyConversionService mICurrencyConversionService;

    @GetMapping("/health")
    public String checkHealth() {

        log.info("::: Health check for Currency Conversion Service");

        return "::: Welcome to Currency Conversion Service :::";
    }

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<CurrencyConversion> requestCurrencyConversion(
            @PathVariable("from") String from,
            @PathVariable("to") String to,
            @PathVariable("quantity") String quantity)
            throws JsonProcessingException {

        final CurrencyConversion conversionExchange =
                mICurrencyConversionService.getCurrencyExchange(from, to);

        BigDecimal amountQuantity = BigDecimal.valueOf(Long.parseLong(quantity));
        MathContext context = new MathContext(4);

        BigDecimal totalAmount =
                conversionExchange.getConversionMultiple().multiply(amountQuantity, context);

        return new ResponseEntity<>(new CurrencyConversion(conversionExchange.getId(),
                                                           conversionExchange.getFrom(),
                                                           conversionExchange.getTo(),
                                                           conversionExchange
                                                                   .getConversionMultiple(),
                                                           conversionExchange.getEnvironment(),
                                                           amountQuantity, totalAmount),
                                    HttpStatus.OK);
    }

    @GetMapping("feign/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<CurrencyConversion> requestCurrencyConversionWithFeignClient(
            @PathVariable("from") String from,
            @PathVariable("to") String to,
            @PathVariable("quantity") String quantity) throws Exception {

        final ResponseEntity<CurrencyExchange> responseEntity =
                mICurrencyConversionServiceFeign.retrieveExchangeValue(from, to);

        final CurrencyExchange conversionExchange = responseEntity.getBody();

        BigDecimal amountQuantity = BigDecimal.valueOf(Long.parseLong(quantity));
        MathContext context = new MathContext(4);

        assert conversionExchange != null;
        BigDecimal totalAmount =
                conversionExchange.getConversionMultiple().multiply(amountQuantity, context);

        return new ResponseEntity<>(new CurrencyConversion(conversionExchange.getId(),
                                                           conversionExchange.getFrom(),
                                                           conversionExchange.getTo(),
                                                           conversionExchange
                                                                   .getConversionMultiple(),
                                                           conversionExchange.getEnvironment(),
                                                           amountQuantity, totalAmount),
                                    HttpStatus.OK);
    }

    public String fallBackResponse(Exception exc) {

        log.error("::: Fallback Method Call :::");

        return  "::: Fail to load, please try again later. :::";
    }

}
