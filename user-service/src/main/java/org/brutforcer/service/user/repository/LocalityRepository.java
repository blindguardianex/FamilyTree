package org.brutforcer.service.user.repository;

import org.brutforcer.service.user.entity.Locality;
import org.brutforcer.service.user.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalityRepository extends JpaRepository<Locality, Long> {

    //select * from localities where name = :name and region_id = :regionId
    Optional<Locality> findByNameAndTypeAndRegion(String name, Locality.Type type, Region region);

    //select * from localities where name = :name and region_id.name = :regionName and region_id.code = :regionCode
    Optional<Locality> findByNameAndTypeAndRegion_NameAndRegion_Code(String name, Locality.Type type, String regionName, String regionCode);
}
