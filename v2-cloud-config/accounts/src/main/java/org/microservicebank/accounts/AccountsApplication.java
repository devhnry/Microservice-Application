package org.microservicebank.accounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import jdk.jfr.Enabled;
import org.microservicebank.accounts.dto.AccountContactInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(AccountContactInfoDto.class)
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts Microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                    name = "Madan Reddy",
                    email = "tutor@eazybytes.com",
                    url = "https://www.eazybytes.com"
                ),
                license = @License(
                    name = "Apache 2.0",
                    url = "https://www.eazybytes.com"
                )
        )
)
public class AccountsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }
}
