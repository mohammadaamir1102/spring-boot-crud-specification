package com.spec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootCrudSpecificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudSpecificationApplication.class, args);
	}

}
