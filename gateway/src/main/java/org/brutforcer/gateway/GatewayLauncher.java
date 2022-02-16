package org.brutforcer.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.brutforcer.gateway.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class GatewayLauncher {

    public static void main(String[] args) {
        SpringApplication.run(GatewayLauncher.class);
    }
}

