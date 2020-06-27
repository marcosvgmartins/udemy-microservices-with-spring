package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningResource {

    /**
     * The first two methods polute the URI, but they are cache-friendly
     * The last two do not polute the URI, but harm caching
     */

    // URI Versioning

    @GetMapping(path = "v1/person")
    public PersonV1 getPersonV1() {
        return new PersonV1("Jay Pritchett");
    }

    @GetMapping(path = "v2/person")
    public PersonV2 getPersonV2() {
        return new PersonV2(new Name("Jay", "Pritchett"));
    }

    // Parameter Versioning

    @GetMapping(path = "person/with-param", params = {"version=1"})
    public PersonV1 getPersonV1WithRequestParameter() {
        return new PersonV1("Jay Pritchett");
    }

    @GetMapping(path = "person/with-param", params = {"version=2"})
    public PersonV2 getPersonV2WithRequestParameter() {
        return new PersonV2(new Name("Jay", "Pritchett"));
    }

    // Header Versioning

    @GetMapping(path = "person/with-header", headers = {"X-API-VERSION=1"})
    public PersonV1 getPersonV1WithHeaderParameter() {
        return new PersonV1("Jay Pritchett");
    }

    @GetMapping(path = "person/with-header", headers = {"X-API-VERSION=2"})
    public PersonV2 getPersonV2WithHeaderParameter() {
        return new PersonV2(new Name("Jay", "Pritchett"));
    }

    // With Content Negotiation or Accept Produces
    // Mime Type versioning

    @GetMapping(path = "person/with-produces", produces = {"application/my.company.app-v1+json"})
    public PersonV1 getPersonV1WithProduces() {
        return new PersonV1("Jay Pritchett");
    }

    @GetMapping(path = "person/with-produces", produces = {"application/my.company.app-v2+json"})
    public PersonV2 getPersonV2WithProduces() {
        return new PersonV2(new Name("Jay", "Pritchett"));
    }
}