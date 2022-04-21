package org.brutforcer.service.user.repository;

import org.brutforcer.service.user.entity.Country;
import org.brutforcer.service.user.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    //select * from regions where name = :name, code = :code
    Optional<Region> findByNameAndCode(String name, String code);

    Optional<Region> findByNameAndCodeAndCountry_NameAndCountry_Code(String name, String code, String countryName, String countryCode);
}
