package org.brutforcer.service.user.service;

import org.brutforcer.common.exceptions.EntityAlreadyExist;
import org.brutforcer.service.user.entity.User;

public interface UserRegistryService {

    User registry(User user);
}
