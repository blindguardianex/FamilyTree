package org.brutforcer.service.user.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.brutforcer.service.user.controller.UserManagementResource;
import org.brutforcer.service.user.dto.UserRegistryDto;
import org.brutforcer.service.user.entity.User;
import org.brutforcer.service.user.service.UserRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserManagementResourceRest implements UserManagementResource {

    private final UserRegistryService registryService;

    @Autowired
    public UserManagementResourceRest(UserRegistryService registryService) {
        this.registryService = registryService;
    }

    @Override
    public ResponseEntity<User> signUp(UserRegistryDto registryDto) {
        log.debug("Received request for registry new user");
        User registeredUser = registryService.registry(registryDto.toUser());
        return ResponseEntity.ok(registeredUser);
    }

    @Override
    public ResponseEntity<User> profileById(long userId) {
        return null;
    }

    @Override
    public ResponseEntity<User> profileByUsername(String username) {
        return null;
    }
}
