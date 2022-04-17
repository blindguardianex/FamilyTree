package org.brutforcer.service.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.brutforcer.common.exceptions.NonExistEntity;
import org.brutforcer.service.user.entity.Locality;
import org.brutforcer.service.user.repository.LocalityRepository;
import org.brutforcer.service.user.service.LocalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JpaLocalityService implements LocalityService {

    private final LocalityRepository repository;

    @Autowired
    public JpaLocalityService(LocalityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Locality saveOrLoad(Locality locality) {
        log.debug("IN saveOrLoad -> {}", locality);
        if (locality.getId() != null)
            return saveOrGetById(locality);

        if (locality.getRegion().getId() != null)
            return saveOrGetByNameAndRegionId(locality);

        return saveOrGetByNameAndRegionNameAndRegionCode(locality);
    }

    @Override
    public void delete(Locality locality) {
        repository.delete(locality);
        log.info("Deleted locality: {}", locality);
    }

    private Locality saveOrGetById(Locality locality) {
        return repository.findById(locality.getId())
                .map(existing -> {
                    log.info("IN saveOrGetById -> loaded locality with id: {}", existing.getId());
                    return existing;
                })
                .orElseThrow(() -> {
                    log.error("IN saveOrGetById -> locality with id {} not exist", locality.getId());
                    throw new NonExistEntity("Locality with id " + locality.getId() + " not exist");
                });
    }

    private Locality saveOrGetByNameAndRegionId(Locality locality) {
        return repository.findByNameAndRegion(locality.getName(), locality.getRegion())
                .map(existing -> {
                    log.info("IN saveOrGetByNameAndRegionId -> locality with name {} and region id {} already exist with id: {}. Loaded", locality.getName(), locality.getRegion().getId(), existing.getId());
                    return existing;
                })
                .orElseGet(() -> {
                    var saved = repository.save(locality);
                    log.info("IN saveOrGetByNameAndRegionId -> saved locality with name {} and region id {} with id: {}", locality.getName(), locality.getRegion().getId(), saved.getId());
                    return saved;
                });
    }

    private Locality saveOrGetByNameAndRegionNameAndRegionCode(Locality locality) {
        return repository.findByNameAndRegion_NameAndRegion_Code(locality.getName(), locality.getRegion().getName(), locality.getRegion().getCode())
                .map(existing -> {
                    log.info("IN saveOrGetByNameAndRegionNameAndRegionCode -> locality with name {} and region name {} and region code {} already exist with id: {}. Loaded", locality.getName(), locality.getRegion().getName(), locality.getRegion().getCode(), existing.getId());
                    return existing;
                })
                .orElseGet(() -> {
                    var saved = repository.save(locality);
                    log.info("IN saveOrGetByNameAndRegionNameAndRegionCode -> saved locality with name {} and region name {} and region code {} with id: {}", locality.getName(), locality.getRegion().getName(), locality.getRegion().getCode(), saved.getId());
                    return saved;
                });
    }
}
