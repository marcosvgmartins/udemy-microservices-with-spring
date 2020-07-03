package com.in28minutes.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeValueRepository repository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

        ExchangeValue exchange = repository.findByFromAndTo(from, to);
        exchange.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

        logger.info("{}", exchange);

        return exchange;
        /*return new ExchangeValue(
            1000l,
            "USD",
            "BRL",
            BigDecimal.valueOf(5.5),
            Integer.parseInt(environment.getProperty("local.server.port")));*/
    }
}