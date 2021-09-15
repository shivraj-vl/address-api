package com.org.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Shivraj This class boot straps API for address application
 */
@SpringBootApplication
public class AddressApiApplication {
	/**
	 * @param args
	 *            This method starts the application engine
	 */
	public static void main(String[] args) {
		SpringApplication.run(AddressApiApplication.class, args);
	}
}
