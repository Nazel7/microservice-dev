package com.microservice.currencyexchange.interfaces;

import com.microservice.currencyexchange.entities.CurrencyExchange;

import javassist.NotFoundException;

public interface ICurrencyExchangeService {

    CurrencyExchange findByFromAndTo(String from, String to) throws Exception;
}
