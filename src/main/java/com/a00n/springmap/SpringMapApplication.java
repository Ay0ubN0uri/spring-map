package com.a00n.springmap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = { "com.a00n.entities" })
@ComponentScan(basePackages = { "com.a00n.controllers" })
@EnableJpaRepositories(basePackages = { "com.a00n.repositories" })
public class SpringMapApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMapApplication.class, args);
	}

}
