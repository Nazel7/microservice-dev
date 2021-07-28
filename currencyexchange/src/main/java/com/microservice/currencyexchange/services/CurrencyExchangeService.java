package com.microservice.currencyexchange.services;

import com.microservice.currencyexchange.entities.CurrencyExchange;
import com.microservice.currencyexchange.interfaces.ICurrencyExchangeService;
import com.microservice.currencyexchange.repositories.ICurrencyExchangeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CurrencyExchangeService implements ICurrencyExchangeService {

    private final ICurrencyExchangeRepo mICurrencyExchangeRepo;

    @Override
    public CurrencyExchange findByFromAndTo(String from, String to) throws Exception {

        final CurrencyExchange currencyExchange = mICurrencyExchangeRepo.findByFromAndTo(from, to);

        if (currencyExchange == null) {

            log.error("::: Item not found :::");
            throw new Exception("Item not found");
        }
        return currencyExchange;

    }
}
