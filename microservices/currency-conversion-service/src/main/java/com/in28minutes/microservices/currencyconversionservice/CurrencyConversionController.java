package com.in28minutes.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

    @Autowired
    Environment environment;

    @Autowired
    CurrencyExchangeServiceProxy proxy;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(
        @PathVariable String from,
        @PathVariable String to,
        @PathVariable BigDecimal quantity) {

        /**
         * Invoke another service and get the response
         * We'll define CurrencyConversionBean as the expected response, although it is
         * not exactly an CurrencyConversionBean, but close enough
         *
         * The code below is cumbersome. There are a lot of things involved for just calling
         * another microservice. That's one of the problems that Feign solves
         */
        Map<String, String> uriVariables = new HashMap<String, String>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
            "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
            CurrencyConversionBean.class,
            uriVariables);

        CurrencyConversionBean responseBean = responseEntity.getBody();

        return new CurrencyConversionBean(
            responseBean.getId(),
            from,
            to,
            responseBean.getConversionMultiple(),
            quantity,
            quantity.multiply(responseBean.getConversionMultiple()),
            //Integer.parseInt(environment.getProperty("local.server.port")));
            responseBean.getPort());
    }

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyWithFeign(
        @PathVariable String from,
        @PathVariable String to,
        @PathVariable BigDecimal quantity) {

        /**
         * With Feign, it is much simpler to make calls to another service
         */
        CurrencyConversionBean responseBean = proxy.retrieveExchangeValue(from, to);

        return new CurrencyConversionBean(
            responseBean.getId(),
            from,
            to,
            responseBean.getConversionMultiple(),
            quantity,
            quantity.multiply(responseBean.getConversionMultiple()),
            //Integer.parseInt(environment.getProperty("local.server.port")));
            responseBean.getPort());
    }
}