package org.brutforcer.service.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.brutforcer.common.exceptions.NonExistEntity;
import org.brutforcer.service.user.entity.Country;
import org.brutforcer.service.user.repository.CountryRepository;
import org.brutforcer.service.user.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JpaCountryService implements CountryService {

    private final CountryRepository repository;

    @Autowired
    public JpaCountryService(CountryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Country saveOrLoad(Country country) {
        log.debug("IN saveOrLoad -> {}", country);
        if (country.getId() != null)
            return getById(country.getId());

        return saveOrGetByNameAndCode(country);
    }

    private Country getById(long id) {
        return repository.findById(id)
                .map(existing -> {
                    log.info("IN getById -> loaded country with id: {}", existing.getId());
                    return existing;
                })
                .orElseThrow(() -> {
                    log.error("IN getById -> country with id {} not exist", id);
                    throw new NonExistEntity("Сountry with id " + id + " not exist");
                });
    }

    private Country saveOrGetByNameAndCode(Country country) {
        return repository.findByNameAndAndCode(country.getName(), country.getCode())
                .map(existing -> {
                    log.info("IN saveOrGetByNameAndCode -> country with name {} and code {} already exist with id: {}. Loaded.", country.getName(),country.getCode(), existing.getId());
                    return existing;
                })
                .orElseGet(() -> {
                    var saved = repository.save(country);
                    log.info("IN saveOrGetByNameAndCode -> saved country with name {}  code {} with id: {}", country.getName(), country.getCode(), saved.getId());
                    return saved;
                });
    }
}
