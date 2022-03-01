package org.brutforcer.service.user.service;

import com.fasterxml.jackson.annotation.OptBoolean;
import org.brutforcer.service.user.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Role add(Role role);
    Role update(Role role);
    List<Role>getAll();
    Optional<Role> getById(long id);
    Optional<Role> getByName(String name);
}
