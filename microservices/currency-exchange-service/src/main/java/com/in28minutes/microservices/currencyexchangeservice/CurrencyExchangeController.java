package com.in28minutes.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @Autowired
    Environment environment;

    @Autowired
    ExchangeValueRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

        ExchangeValue exchange = repository.findByFromAndTo(from, to);
        exchange.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return exchange;
        /*return new ExchangeValue(
            1000l,
            "USD",
            "BRL",
            BigDecimal.valueOf(5.5),
            Integer.parseInt(environment.getProperty("local.server.port")));*/
    }
}