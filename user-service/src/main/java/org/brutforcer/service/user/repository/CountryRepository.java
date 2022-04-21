package org.brutforcer.service.user.repository;

import org.brutforcer.service.user.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    //select * from countries where name = :name and code = :code
    Optional<Country> findByNameAndAndCode(String name, String code);
}
