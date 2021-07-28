package com.microservice.limitservice.controllers;

import com.microservice.limitservice.limit.Limit;
import com.microservice.limitservice.configs.LimitConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("limits")
public class LimitController {

    private final LimitConfiguration mLimitConfiguration;

    @GetMapping(" ")
    public ResponseEntity<Limit> retrieveLimits(){

        log.info("::: Returning limit with minVal: [{}] and maxVal: [{}] :::",
                 mLimitConfiguration.getMin(), mLimitConfiguration.getMax());

        return new ResponseEntity<>(new Limit(mLimitConfiguration.getMin(),
                                              mLimitConfiguration.getMax()), HttpStatus.OK);
    }
}
