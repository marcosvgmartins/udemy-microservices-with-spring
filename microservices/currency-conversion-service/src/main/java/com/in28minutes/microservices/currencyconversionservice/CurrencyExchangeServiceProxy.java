package com.in28minutes.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * The Feign proxy will hide all the complexity of talking to other services
 *
 * Ribbon provides load balancing, so that one service can talk to multiple instances
 * of another service. So, when we add Ribbon, we can stop hard-coding the url of the
 * service we want to communicate with (first it comes from the properties file, then from
 * the name server)
 *
 * After we added the Zuul API Gateway, we changed the endpoint at the FeignClient annotation
 * so that the request would be sent through the API gateway
 */
//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
//@FeignClient(name = "currency-exchange-service")
@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    /**
     * The GetMapping path also changed after after adding Zuul, since we now have to prefix
     * it with the application name (the same name that the exchange service registers in Eureka)
     */
    //@GetMapping("/currency-exchange/from/{from}/to/{to}")
    @GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionBean retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}