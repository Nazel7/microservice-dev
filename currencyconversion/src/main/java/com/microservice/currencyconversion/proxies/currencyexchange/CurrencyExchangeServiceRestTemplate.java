package com.microservice.currencyconversion.proxies.currencyexchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.currencyconversion.proxies.currencyexchange.configs.CurrencyExchangeConfig;
import com.microservice.currencyconversion.entities.CurrencyConversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.security.InvalidParameterException;
import java.util.HashMap;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CurrencyExchangeServiceRestTemplate {

    private final CurrencyExchangeConfig mCurrencyExchangeConfig;
    private final RestTemplate mRestTemplate;

    private static final String CONVERSION_EXCHANGE_PATH= "%s/currency-exchange/from/{from}/to/{to}";

    public CurrencyConversion getCurrencyExchange(String from, String to)
            throws JsonProcessingException {
        try {
            HashMap<String, String> uriVariables= new HashMap<>();
            uriVariables.put("from", from);
            uriVariables.put("to", to);

            final String requestUrl =
                    String.format(CONVERSION_EXCHANGE_PATH, mCurrencyExchangeConfig.getUrl());

            final ResponseEntity<CurrencyConversion> responseResponseEntity =
                    mRestTemplate.getForEntity(requestUrl, CurrencyConversion.class, uriVariables);

            return responseResponseEntity.getBody();
        } catch (final HttpClientErrorException ex) {
            final ObjectMapper mapper = new ObjectMapper();
            final String errorResponse =
                    mapper.readValue(ex.getResponseBodyAsString(), String.class);
            throw new InvalidParameterException(errorResponse);
        }
    }

}
