package org.brutforcer.service.user.service;

import org.brutforcer.service.user.entity.User;

import java.util.Optional;

public interface UserService {

    User add(User user);
    User update(User user);
    Optional<User> getById(long id);
}
