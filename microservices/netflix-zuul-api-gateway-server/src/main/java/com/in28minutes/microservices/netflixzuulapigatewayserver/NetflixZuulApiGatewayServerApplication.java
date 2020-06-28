package com.in28minutes.microservices.netflixzuulapigatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * This is our API Gateway. It is able to provide some centralized features, such as
 * logging, authentication and authorization, rate limiting and service aggregation
 *
 * The EnableZuulProxy annotation is the one responsible for making this service work
 * as an API Gateway
 *
 * To send a request through the API Gatexay, the format is: http://localhost:8765/{service}/{uri}
 * For example: http://localhost:8765/currency-exchange-service/currency-exchange/from/USD/to/INR
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class NetflixZuulApiGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixZuulApiGatewayServerApplication.class, args);
	}

}
