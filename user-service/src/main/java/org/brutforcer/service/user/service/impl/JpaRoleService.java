package org.brutforcer.service.user.service.impl;

import org.brutforcer.service.user.entity.Role;
import org.brutforcer.service.user.repository.RoleRepository;
import org.brutforcer.service.user.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaRoleService implements RoleService {

    private final RoleRepository repository;

    public JpaRoleService(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role add(Role role) {
        return repository.saveAndFlush(role);
    }

    @Override
    public Role update(Role role) {
        return repository.saveAndFlush(role);
    }

    @Override
    public List<Role> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Role> getById(long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Role> getByName(String name) {
        return repository.getByName(name);
    }
}
