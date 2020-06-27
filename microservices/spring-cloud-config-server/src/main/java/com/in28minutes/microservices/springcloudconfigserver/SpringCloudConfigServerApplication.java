package com.in28minutes.microservices.springcloudconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * The EnableConfigServer will allow the app to return configuration for
 * different microservices and environments.
 *
 * For example, to retrieve the QA environment config for limits-service:
 * GET http://localhost:8888/limits-service/qa
 *
 * The property files must be commited to a local Git repository for them
 * to be picked up (the repository URI is defined in this project's application.properties)
 */
@EnableConfigServer
@SpringBootApplication
public class SpringCloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigServerApplication.class, args);
	}

}
