package org.brutforcer.service.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.brutforcer.common.exceptions.NonExistEntity;
import org.brutforcer.service.user.entity.Locality;
import org.brutforcer.service.user.repository.LocalityRepository;
import org.brutforcer.service.user.service.LocalityService;
import org.brutforcer.service.user.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JpaLocalityService implements LocalityService {

    private final LocalityRepository repository;
    private final RegionService regionService;

    @Autowired
    public JpaLocalityService(LocalityRepository repository, RegionService regionService) {
        this.repository = repository;
        this.regionService = regionService;
    }

    @Override
    public Locality saveOrLoad(Locality locality) {
        log.debug("IN saveOrLoad -> {}", locality);
        if (locality.getId() != null)
            return getById(locality);

        checkNotNullRegion(locality);
        if (locality.getRegion().getId() != null)
            return saveOrGetByNameAndRegionId(locality);

        return saveOrGetByNameAndRegionNameAndRegionCode(locality);
    }

    @Override
    public void delete(Locality locality) {
        repository.delete(locality);
        log.info("IN delete -> deleted locality: {}", locality);
    }

    private Locality getById(Locality locality) {
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
        return repository.findByNameAndTypeAndRegion(locality.getName(), locality.getType(), locality.getRegion())
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
        return repository.findByNameAndTypeAndRegion_NameAndRegion_Code(locality.getName(), locality.getType(), locality.getRegion().getName(), locality.getRegion().getCode())
                .map(existing -> {
                    log.info("IN saveOrGetByNameAndRegionNameAndRegionCode -> locality with name {} and region name {} and region code {} already exist with id: {}. Loaded", locality.getName(), locality.getRegion().getName(), locality.getRegion().getCode(), existing.getId());
                    return existing;
                })
                .orElseGet(() -> {

                    if (locality.getRegion().getId() == null)
                        locality.setRegion(regionService.saveOrLoad(locality.getRegion()));

                    var saved = repository.save(locality);
                    log.info("IN saveOrGetByNameAndRegionNameAndRegionCode -> saved locality with name {} and region name {} and region code {} with id: {}", locality.getName(), locality.getRegion().getName(), locality.getRegion().getCode(), saved.getId());
                    return saved;
                });
    }

    private void checkNotNullRegion(Locality locality) {
        if (locality.getRegion() == null){
            log.error("Cannot save or load locality {} without region", locality);
            throw new IllegalArgumentException("Cannot save or load locality without region");
        }
    }
}
