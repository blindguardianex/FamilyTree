package org.brutforcer.service.user.service.impl;

import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.brutforcer.common.Caches;
import org.brutforcer.common.exceptions.UpdatingNonExistEntity;
import org.brutforcer.service.user.entity.Role;
import org.brutforcer.service.user.repository.RoleRepository;
import org.brutforcer.service.user.service.RoleService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class JpaRoleService implements RoleService {

    private final RoleRepository repository;
    private final LoadingCache<String, Optional<Role>> cacheByName;

    public JpaRoleService(RoleRepository repository) {
        this.repository = repository;
        cacheByName = Caches.simpleCache(
                repository::getByName,
                10,
                Duration.ofDays(1)
        );
    }

    @Override
    public Role add(Role role) {
        log.debug("IN add -> creating new role: {}", role.getName());
        Optional<Role> roleOpt = this.getByName(role.getName());
        return roleOpt.map(existing -> {
                    log.warn("IN add -> role \"{}\" already exist with id {}", existing.getName(), existing.getId());
                    return existing;
                })
                .orElseGet(() -> {
                    Role saved = repository.saveAndFlush(role);
                    log.info("IN add -> role \"{}\" successfully created", saved.getName());
                    return role;
                });
    }

    @Override
    public Role update(Role role) {
        if (role.getId() == null)
            throw new UpdatingNonExistEntity("Was attempt updating role, but: role id is null");

        log.debug("IN update -> updating role: {}", role.getName());
        Optional<Role> roleOpt = this.getById(role.getId());
        return roleOpt.map(existing -> {
                    Role updated = repository.saveAndFlush(role);
                    log.info("IN update -> role \"{}\" (old name \"{}\") successfully updated", updated.getName(), role.getName());
                    cacheByName.put(updated.getName(), Optional.of(updated));
                    return updated;
                })
                .orElseThrow(() -> {
                    log.error("IN update -> Was attempt updating role \"{}\", but: role with id {} not found", role.getName(), role.getId());
                    throw new UpdatingNonExistEntity("Was attempt updating role, but: role with id " + role.getId() + " not found");
                });
    }

    @Override
    public List<Role> getAll() {
        log.debug("IN getAll -> founding all roles");
        var allRoles = repository.findAll();
        log.info("IN getAll -> was founded {} roles", allRoles.size());
        return allRoles;
    }

    @Override
    public Optional<Role> getById(long id) {
        log.debug("IN getById -> found role by id: {}", id);
        Optional<Role> role = repository.findById(id);
        log.info("IN getById -> by id {} was found role: {}", id, role.isPresent());
        return role;
    }

    @Override
    public Optional<Role> getByName(String name) {
        log.debug("IN getByName -> found role by name: {}", name);
        Optional<Role> role = cacheByName.getUnchecked(name);
        log.info("IN getByName -> by name {} was found role: {}", name, role.isPresent());
        return role;
    }

    @Override
    public void delete(Role role) {
        log.debug("IN delete -> deleting role with name {} and id: {}", role.getName(), role.getId());
        repository.delete(role);
        log.info("IN delete -> role {} successfully deleting", role.getName());
    }
}
