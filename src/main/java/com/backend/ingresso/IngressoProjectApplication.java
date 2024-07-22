package com.backend.ingresso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class IngressoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(IngressoProjectApplication.class, args);
	}

}
