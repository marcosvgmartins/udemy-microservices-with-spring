package com.in28minutes.rest.webservices.restfulwebservices;

/*import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;*/

/**
 * This was required to disable CSRF and thus make it possible to test with
 * Postman more easily.
 *
 * Ref: https://spring.io/blog/2013/08/21/spring-security-3-2-0-rc1-highlights-csrf-protection/
 */

/*@EnableWebSecurity
@Configuration
public class WebConfig extends
   WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
  }
}*/