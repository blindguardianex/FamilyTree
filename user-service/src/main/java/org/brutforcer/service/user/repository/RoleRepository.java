package org.brutforcer.service.user.repository;

import org.brutforcer.service.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    //SELECT * FROM roles r WHERE r.name = :name
    Optional<Role> getByName(String name);
}
