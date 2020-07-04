package com.in28minutes.microservices.limitsservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration configuration;

    private static final int DEFAULT_MIN = 12;

    private static final int DEFAULT_MAX = 28;

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitsFromConfiguration() {
        return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
    }

    /**
     * The idea of this method is to demonstrate how fault tolerance with Hystrix can be enabled
     * We can define a fallback method that gets executed if the target method throws an exception
     */
    @GetMapping("/fault-tolerance-example")
    @HystrixCommand(fallbackMethod = "fallbackForRetrieveWithFaultTolerance")
    public LimitConfiguration retrieveWithFaultTolerance() {
        throw new RuntimeException("Hystrix, please come to the rescue!");
    }

    public LimitConfiguration fallbackForRetrieveWithFaultTolerance() {
        return new LimitConfiguration(DEFAULT_MAX, DEFAULT_MIN);
    }
}