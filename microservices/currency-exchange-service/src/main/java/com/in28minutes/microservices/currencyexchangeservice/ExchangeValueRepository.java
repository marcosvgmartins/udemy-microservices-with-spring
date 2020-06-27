package com.in28minutes.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends
    JpaRepository<ExchangeValue, Long> {

    /**
     * Just by defining this method in the interface and following a name convention
     * Spring Data JPA is able to provide the correct implementation and query generation
     *
     * @param from
     * @param to
     * @return
     */
    ExchangeValue findByFromAndTo(String from, String to);
}