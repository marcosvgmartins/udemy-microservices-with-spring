package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This resource is used to demonstrate how filtering can be applied to eliminate some fields
 * from the returned resources using JsonIgnore*-style annotations
 */
@RestController
public class FilteringResource {

    @GetMapping("/filtering")
    public SomeBean retrieveSomeBean() {
        return new SomeBean("value1","value2","value3");
    }

    @GetMapping("/filtering-list")
    public List<SomeBean> retrieveSomeBeanList() {
        return Arrays.asList(
            new SomeBean("value1","value2","value3"),
            new SomeBean("value4","value5","value6"));
    }

    @GetMapping("/dynamic-filtering")
    public MappingJacksonValue retrieveSomeBeanDynamicFilter() {
        SomeBean value = new SomeBean("value1","value2","value3");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");

        FilterProvider filters = new SimpleFilterProvider().addFilter("MySomeBeanFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(value);
        mapping.setFilters(filters);

        return mapping;
    }
}