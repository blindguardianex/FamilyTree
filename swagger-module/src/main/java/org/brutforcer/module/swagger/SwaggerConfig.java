package org.brutforcer.module.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@ConditionalOnProperty(name = "application.swagger.enabled", havingValue = "true")
public class SwaggerConfig {

    @Bean
    @Autowired
    public OpenAPI openApi(Info info, List<Server> servers){
        return new OpenAPI()
                .info(info)
                .servers(servers);
    }

    @Bean
    public Info info(@Value("${application.swagger.title}") String swaggerTitle,
                      @Value("${application.swagger.description}") String swaggerDescription,
                      @Value("${application.swagger.version}") String swaggerVersion) {
        return new Info()
                .title(swaggerTitle)
                .description(swaggerDescription)
                .version(swaggerVersion)
                .contact(new Contact().name("Titov Dmitry").url("https://github.com/blindguardianex/"));
    }

    @Bean
    public List<Server> servers(@Value("${application.swagger.servers}") String[] swaggerServers) {
        return Arrays.stream(swaggerServers)
                        .map(server->server.split("::"))
                        .map(server->new Server()
                                .url(server[0].trim())
                                .description(server[1].trim())
                        ).collect(Collectors.toList());
    }
}
