package org.brutforcer.service.user.service.impl;

import org.brutforcer.service.user.entity.Role;
import org.brutforcer.service.user.repository.RoleRepository;
import org.brutforcer.service.user.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaRoleService implements RoleService {

    private final RoleRepository roleRepository;

    public JpaRoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role add(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public Optional<Role> getById(Long id) {
        return roleRepository.findById(id);
    }
}
