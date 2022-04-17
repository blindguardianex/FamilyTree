package org.brutforcer.service.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.brutforcer.service.user.entity.User;
import org.brutforcer.service.user.repository.UserRepository;
import org.brutforcer.service.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class JpaUserService implements UserService {

    private final UserRepository repository;

    public JpaUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User add(User user) {
        return repository.saveAndFlush(user);
    }

    @Override
    public User update(User user) {
        return repository.saveAndFlush(user);
    }

    @Override
    public Optional<User> getById(long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return repository.getUserByUsername(username);
    }
}
