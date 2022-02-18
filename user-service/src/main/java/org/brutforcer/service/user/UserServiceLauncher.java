package org.brutforcer.service.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceLauncher {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceLauncher.class);
    }
}
