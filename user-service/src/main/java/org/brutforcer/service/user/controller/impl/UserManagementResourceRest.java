package org.brutforcer.service.user.controller.impl;

import org.brutforcer.service.user.controller.UserManagementResource;
import org.brutforcer.service.user.dto.UserRegistryDto;
import org.brutforcer.service.user.entity.User;
import org.brutforcer.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserManagementResourceRest implements UserManagementResource {

    private final UserService userService;

    @Autowired
    public UserManagementResourceRest(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<User> signUp(UserRegistryDto registryDto) {
        return null;
    }

    @Override
    public ResponseEntity<User> profileById(long userId) {
        return null;
    }
}
