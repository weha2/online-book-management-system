package com.weha.online_book_management_system.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenConfig {

    @Bean
    public OpenAPI customeOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Online Book Management System")
                        .version("1.0")
                        .description("This is Online Book Management System Service.")
                        .contact(new Contact()
                                .email("weha.kriadchaingam@gmail.com")
                                .name("Mr.Weha Kriadchaingam")
                        )
                );
    }
}
