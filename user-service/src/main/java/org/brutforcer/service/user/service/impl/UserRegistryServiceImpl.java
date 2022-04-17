package org.brutforcer.service.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.brutforcer.service.user.entity.Role;
import org.brutforcer.service.user.entity.User;
import org.brutforcer.service.user.service.RoleService;
import org.brutforcer.service.user.service.UserRegistryService;
import org.brutforcer.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Slf4j
@Service
public class UserRegistryServiceImpl implements UserRegistryService {

    private static final String DEFAULT_ROLE_NAME = "USER";
    private final PasswordEncoder encoder;
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserRegistryServiceImpl(PasswordEncoder encoder, UserService userService, RoleService roleService) {
        this.encoder = encoder;
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public User registry(User user) {
        log.info("IN registry -> registration user: login {}, email {}, FIO: {} {} {}", user.getUsername(), user.getProfile().getEmail(), user.getProfile().getLastName(), user.getProfile().getFirstName(), user.getProfile().getOtherName());
        Role defaultRole = roleService.getByName(DEFAULT_ROLE_NAME).orElseThrow(NullPointerException::new);
        user.addRole(defaultRole);
        user = userService.add(encodePassword(user));
        log.info("IN registry -> successfully registration user: {}", user.getUsername());
        return user;
    }

    private User encodePassword(User user) {
        String encoded = encoder.encode(user.getPassword());
        user.setPassword(encoded);
        return user;
    }
}
