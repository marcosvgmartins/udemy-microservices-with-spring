package com.in28minutes.springboot.basics.springbootin10steps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

// Spring annotation that defines this as a Spring context, enables component scan and auto-configuration
// Auto-configuration checks what is in the classpath and performs the necessary configuration
// automatically
@SpringBootApplication
public class SpringbootIn10StepsApplication {

	public static void main(String[] args) {
		ApplicationContext appContext =
			SpringApplication.run(SpringbootIn10StepsApplication.class, args);

		for (String beanName : appContext.getBeanDefinitionNames()) {
			System.out.println(beanName);
		}
	}

}
