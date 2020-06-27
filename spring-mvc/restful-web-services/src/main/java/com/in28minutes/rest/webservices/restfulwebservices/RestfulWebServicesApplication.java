package com.in28minutes.rest.webservices.restfulwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class RestfulWebServicesApplication {

	/**
	 * Notes:
	 *
	 * - Since we chose the starter-web dependency, a DispatcherServlet has been found in the classpath, and therefore
	 * has been automatically configured by DispatcherServletAutoConfiguration
	 * - ErrorMvcAutoConfiguration as well, so a basic error page is configured as well
	 * - This is all because of Spring Boot Autoconfiguration
	 * - HttpMessageConverters are automatically added as well, allowing a bean to be automatically converted to JSON
	 *
	 * - The Dispatcher Servlet controls the root "/" of the REST API. It is the front controller for Spring MVC
	 *
	 * - As Spring Boot Actuator has been added as a dependency, you can browse to http://localhost:8080/actuator
	 * - The HAL Browser has also been added and is available at http://localhost:8080
	 *
	 * - Basic Authentication is automatically configured as we add spring-boot-starter-security to pom.xml
	 */

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		//SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	/*
	* This can be commented out because you can configure it directly in the application.properties
	* file
	@Bean
	public ResourceBundleMessageSource bundleMessageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("messages");
		return source;
	}*/
}
