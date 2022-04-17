package org.brutforcer.service.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.brutforcer.common.exceptions.NonExistEntity;
import org.brutforcer.service.user.entity.Country;
import org.brutforcer.service.user.entity.Region;
import org.brutforcer.service.user.repository.RegionRepository;
import org.brutforcer.service.user.service.CountryService;
import org.brutforcer.service.user.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JpaRegionService implements RegionService {

    private final RegionRepository repository;
    private final CountryService countryService;

    @Autowired
    public JpaRegionService(RegionRepository repository, CountryService countryService) {
        this.repository = repository;
        this.countryService = countryService;
    }

    @Override
    public Region saveOrLoad(Region region) {
        log.debug("IN saveOrLoad -> {}", region);
        if (region.getId() != null)
            return saveOrGetById(region);

        return saveOrGetByNameAndCode(region);
    }

    @Override
    public void delete(Region region) {
        repository.delete(region);
        log.info("Deleted region: {}", region);
    }

    private Region saveOrGetById(Region region) {
        return repository.findById(region.getId())
                .map(existing -> {
                    log.info("IN saveOrGetById -> loaded region with id: {}", existing.getId());
                    return existing;
                })
                .orElseThrow(() -> {
                    log.error("IN saveOrGetById -> region with id {} not exist", region.getId());
                    throw new NonExistEntity("Region with id " + region.getId() + " not exist");
                });
    }

    private Region saveOrGetByNameAndCode(Region region) {
        return repository.findByNameAndCode(region.getName(), region.getCode())
                .map(existing -> {
                    log.info("IN saveOrGetByNameAndCode -> region with name {} and code {} already exist with id: {}. Loaded", region.getName(), region.getCode(), existing.getId());
                    return existing;
                })
                .orElseGet(() -> {
                    checkNotNullCountry(region);

                    if (region.getCountry().getId() == null)
                        region.setCountry(countryService.saveOrLoad(region.getCountry()));

                    var saved = repository.save(region);
                    log.info("IN saveOrGetByNameAndCode -> saved region with name {} and code {} with id: {}", region.getName(), region.getCode(), saved.getId());
                    return saved;
                });
    }

    private void checkNotNullCountry(Region region) {
        if (region.getCountry() == null){
            log.error("Cannot save region {} without country", region);
            throw new IllegalArgumentException("Cannot save region without country");
        }
    }
}
