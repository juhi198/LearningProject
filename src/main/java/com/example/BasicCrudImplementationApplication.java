package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BasicCrudImplementationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicCrudImplementationApplication.class, args);
	}

}
