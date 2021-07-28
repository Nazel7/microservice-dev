package com.microservice.currencyexchange.repositories;

import com.microservice.currencyexchange.entities.CurrencyExchange;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICurrencyExchangeRepo extends JpaRepository<CurrencyExchange, Long> {

    CurrencyExchange findByFromAndTo(String from, String to);
}
