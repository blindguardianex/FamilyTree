package org.brutforcer.service.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.brutforcer.common.exceptions.EntityAlreadyExist;
import org.brutforcer.common.exceptions.NonExistEntity;
import org.brutforcer.service.user.entity.User;
import org.brutforcer.service.user.repository.UserRepository;
import org.brutforcer.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class JpaUserService implements UserService {

    private final UserRepository repository;

    @Autowired
    public JpaUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user){
        log.debug("IN create -> creating user: {}", user);
        var byUsername = getByUsername(user.getUsername());
        if (byUsername.isPresent()) {
            log.error("IN create -> was attempt creating user with username {}, but: already exist", user.getUsername());
            throw new EntityAlreadyExist("User with username " + user.getUsername() + " already exist");
        }
        var byEmail = getByEmail(user.getProfile().getEmail());
        if (byEmail.isPresent()) {
            log.error("IN create -> was attempt creating user with email {}, but: already exist", user.getProfile().getEmail());
            throw new EntityAlreadyExist("User with email " + user.getProfile().getEmail() + " already exist");
        }
        var byPhoneNumber = getByPhoneNumber(user.getProfile().getPhoneNumber());
        if (byPhoneNumber.isPresent()) {
            log.error("IN create -> was attempt creating user with phone number {}, but: already exist", user.getProfile().getPhoneNumber());
            throw new EntityAlreadyExist("User with phone number " + user.getProfile().getPhoneNumber() + " already exist");
        }

        var saved = repository.saveAndFlush(user);
        log.info("IN create -> User with username {} successfully created with id: {}", saved.getUsername(), saved.getId());
        return saved;
    }

    @Override
    public User update(User user) {
        log.debug("IN update -> creating user: {}", user);
        var byId = getById(user.getId());
        if (byId.isEmpty()) {
            log.error("IN update -> was attempt creating user with id {}, but: not exist", user.getUsername());
            throw new NonExistEntity("User with id " + user.getId() + " not exist");
        }
        var updated = repository.saveAndFlush(user);
        log.info("IN update -> User with id {} successfully updated", updated.getId());
        return updated;
    }

    @Override
    public Optional<User> getById(long id) {
        log.debug("IN getById -> loading user with id: {}", id);
        var user = repository.findById(id);
        if (user.isPresent())
            log.info("IN getById -> loaded user with id: {}", id);
        else
            log.info("IN getById -> user with id {} not found", id);
        return user;
    }

    @Override
    public Optional<User> getByUsername(String username) {
        log.debug("IN getByUsername -> loading user with username: {}", username);
        var user = repository.getUserByUsername(username);
        if (user.isPresent())
            log.info("IN getByUsername -> loaded user with username: {}", username);
        else
            log.info("IN getByUsername -> user with username {} not found", username);
        return user;
    }

    @Override
    public Optional<User> getByEmail(String email) {
        log.debug("IN getByEmail -> loading user with email: {}", email);
        var user = repository.getByProfileEmail(email);
        if (user.isPresent())
            log.info("IN getByEmail -> loaded user with email: {}", email);
        else
            log.info("IN getByEmail -> user with email {} not found", email);
        return user;
    }

    @Override
    public Optional<User> getByPhoneNumber(String phoneNumber) {
        log.debug("IN getByPhoneNumber -> loading user with phoneNumber: {}", phoneNumber);
        var user = repository.getByProfilePhoneNumber(phoneNumber);
        if (user.isPresent())
            log.info("IN getByPhoneNumber -> loaded user with phoneNumber: {}", phoneNumber);
        else
            log.info("IN getByPhoneNumber -> user with phoneNumber {} not found", phoneNumber);
        return user;
    }
}
