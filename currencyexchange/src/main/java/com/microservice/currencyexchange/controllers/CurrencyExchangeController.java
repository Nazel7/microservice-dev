package com.microservice.currencyexchange.controllers;

import com.microservice.currencyexchange.entities.CurrencyExchange;
import com.microservice.currencyexchange.interfaces.ICurrencyExchangeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    private final Environment mEnvironment;

    private final ICurrencyExchangeService mICurrencyExchangeService;

    @GetMapping("/health")
    public String checkHealth() {

        log.info("::: Currency Exchange Service health check :::");

        return "::: Welcome to Currency-Exchange-Service :::";

    }

    @CrossOrigin
    @GetMapping("/from/{from}/to/{to}")
    public ResponseEntity<CurrencyExchange> retrieveExchangeValue(@PathVariable("from") String from,
                                                                  @PathVariable("to") String to)
            throws Exception {

        final String port = mEnvironment.getProperty("server.port");
        final CurrencyExchange currencyExchange =
                mICurrencyExchangeService.findByFromAndTo(from, to);

        currencyExchange.setEnvironment(port);
        return new ResponseEntity<>(currencyExchange, HttpStatus.OK);
    }

    public String fallbackMethod(Exception ex) {

        log.info("This is a FallBack Method call :::");
        return "::: Fails to load, please try again :::";
    }

}
