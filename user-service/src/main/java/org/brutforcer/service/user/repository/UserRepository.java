package org.brutforcer.service.user.repository;

import org.brutforcer.service.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserByUsername(String username);
    Optional<User> getByProfileEmail(String email);
    Optional<User> getByProfilePhoneNumber(String phoneNumber);
}
