package com.ubr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.ubr.model")
@EnableJpaRepositories(basePackages = "com.ubr.repository")
public class UbrApplication {

	public static void main(String[] args) {
		SpringApplication.run(UbrApplication.class, args);
	}
}
