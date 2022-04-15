package org.brutforcer.service.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    //todo: перенести эндпоинт регистрации в данный сервис
    // (все должно остаться, но роль для доступа к ендпоинту
    // регистрации у юзер сервиса должен остаться только у сервера аутентификации.
    // После регистрации юзер сервис кидает событие в очередь событий, а сервер авторизации
    // сохраняет необходимые данные о пользователе в локальной бд).

}
