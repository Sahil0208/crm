package com.amdocs.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * The Class CrmApplication.
 */
@SpringBootApplication
@EnableEurekaClient
public class CrmApplication{
	//extends SpringBootServletInitializer
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(CrmApplication.class, args);
	}

}
