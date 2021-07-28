package com.microservice.currencyconversion.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.currencyconversion.entities.CurrencyConversion;

public interface ICurrencyConversionService {

    CurrencyConversion getCurrencyExchange(String from, String to) throws JsonProcessingException;
}
