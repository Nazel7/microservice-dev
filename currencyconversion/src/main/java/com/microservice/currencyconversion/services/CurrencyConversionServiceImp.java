package com.microservice.currencyconversion.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.currencyconversion.proxies.currencyexchange.CurrencyExchangeServiceRestTemplate;
import com.microservice.currencyconversion.entities.CurrencyConversion;
import com.microservice.currencyconversion.interfaces.ICurrencyConversionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CurrencyConversionServiceImp implements ICurrencyConversionService {

    private final CurrencyExchangeServiceRestTemplate mCurrencyExchangeServiceRestTemplate;

    @Override
    public CurrencyConversion getCurrencyExchange(String from, String to)
            throws JsonProcessingException{

        return mCurrencyExchangeServiceRestTemplate.getCurrencyExchange(from, to);
    }
}
