package com.microservice.currencyconversion.utils;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Base64;

@Configuration
public class RestTemplateUtil {


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(final RestTemplateBuilder builder) {
        return builder.setReadTimeout(Duration.ofMillis(8000))
                      .setConnectTimeout(Duration.ofMillis(8000))
                      .build();
    }
}
