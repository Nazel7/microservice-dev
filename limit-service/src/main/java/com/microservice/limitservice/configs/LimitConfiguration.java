package com.microservice.limitservice.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;


@Data
@Component
@ConfigurationProperties("limit-service")
public class LimitConfiguration {

    private int min;

    private int max;
}
