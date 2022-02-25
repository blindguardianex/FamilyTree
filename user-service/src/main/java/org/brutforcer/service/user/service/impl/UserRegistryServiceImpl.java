package org.brutforcer.service.user.service.impl;

import org.brutforcer.service.user.entity.Role;
import org.brutforcer.service.user.entity.User;
import org.brutforcer.service.user.service.RoleService;
import org.brutforcer.service.user.service.UserRegistryService;
import org.brutforcer.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistryServiceImpl implements UserRegistryService {

    private static final String DEFAULT_ROLE_NAME = "USER";
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserRegistryServiceImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public User registry(User user) {
        Role defaultRole = roleService.getByName(DEFAULT_ROLE_NAME).orElseThrow(NullPointerException::new);
        user.addRole(defaultRole);
        user = userService.add(user);
        return user;
    }
}
