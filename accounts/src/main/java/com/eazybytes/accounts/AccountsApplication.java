package com.eazybytes.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Account microservice Rest API Documentation",
				description = "Prakash Account microservices REST API Documentation",
				version = "V1",
				contact = @Contact(
						name = "Prakash Vinayak",
						email = "tiwariprakash965@gmail.com",
						url = "Prakash.com"
				),
				license = @License(
						name = "Apache2.0",
						url = "prakash.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Prakash Account microservices REST API Documentation",
				url = "prakash.com"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
