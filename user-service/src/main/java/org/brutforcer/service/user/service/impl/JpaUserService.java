package org.brutforcer.service.user.service.impl;

import org.brutforcer.service.user.entity.User;
import org.brutforcer.service.user.repository.UserRepository;
import org.brutforcer.service.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaUserService implements UserService {

    private final UserRepository userRepository;

    public JpaUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User add(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Optional<User> getById(long id) {
        return userRepository.findById(id);
    }
}
