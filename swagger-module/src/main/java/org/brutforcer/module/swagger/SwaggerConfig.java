package org.brutforcer.module.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConditionalOnProperty(name = "application.swagger.enabled", havingValue = "true")
public class SwaggerConfig {

    @Value("${application.swagger.title}")
    private String swaggerTitle;

    @Value("${application.swagger.description}")
    private String swaggerDescription;

    @Value("${application.swagger.version}")
    private String swaggerVersion;

    @Bean
    public OpenAPI openApi(){
        return new OpenAPI()
                .info(info())
                .servers(servers());
    }

    private Info info() {
        return new Info()
                .title(swaggerTitle)
                .description(swaggerDescription)
                .version(swaggerVersion)
                .contact(new Contact().name("Titov Dmitry").url("https://github.com/blindguardianex/"));
    }

    private List<Server> servers() {
        return List.of(
                new Server().url("http://localhost:8080/family-tree").description("Внешний API (шлюз) приложения"),
                new Server().url("http://localhost:8081/auth").description("Сервер авторизации"),
                new Server().url("http://localhost:8082/user-service").description("Сервис управления профилями пользователей"));
    }
}
