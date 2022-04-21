package org.brutforcer.service.user.service;

import org.brutforcer.common.exceptions.EntityAlreadyExist;
import org.brutforcer.service.user.entity.User;

import java.util.Optional;

public interface UserService {

    User create(User user) throws EntityAlreadyExist;
    User update(User user);
    Optional<User> getById(long id);
    Optional<User> getByUsername(String username);
}
