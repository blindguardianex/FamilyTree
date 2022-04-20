package org.brutforcer.service.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.brutforcer.service.user.entity.Address;
import org.brutforcer.service.user.repository.AddressRepository;
import org.brutforcer.service.user.service.AddressService;
import org.brutforcer.service.user.service.CountryService;
import org.brutforcer.service.user.service.LocalityService;
import org.brutforcer.service.user.service.RegionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Slf4j
@Service
public class JpaAddressService implements AddressService {

    private final AddressRepository repository;
    private final CountryService countryService;
    private final RegionService regionService;
    private final LocalityService localityService;

    public JpaAddressService(AddressRepository repository,
                             CountryService countryService,
                             RegionService regionService,
                             LocalityService localityService) {
        this.repository = repository;
        this.countryService = countryService;
        this.regionService = regionService;
        this.localityService = localityService;
    }

    @Override
    @Transactional
    public Address registry(Address address) {
        log.debug("IN registry -> creating new address: {}", address.toString());
        createTextAddress(address);
        Optional<Address> addressOpt = repository.findByTextAddress(address.getTextAddress());
        return addressOpt.map(existing -> {
                    log.warn("IN registry -> address \"{}\" already exist with id {}", existing.getTextAddress(), existing.getId());
                    return existing;
                })
                .orElseGet(() -> {
                    Address saved = repository.saveAndFlush(address);
                    log.info("IN registry -> address \"{}\" successfully created with id {}", saved.getTextAddress(), saved.getId());
                    return saved;
                });
    }

    @Override
    @Deprecated
    public Address update(Address address) {
        log.debug("IN registry -> updating address: {}", address.toString());
        if (address.getId() != null) {
            var existing = getById(address.getId());
            if (existing.isPresent()) {
                var saved = repository.saveAndFlush(address);
                log.info("IN update -> address {} successfully updated", address);
                return saved;
            }
        }
        log.error("Cannot updating address without correct id: {}", address);
        throw new IllegalArgumentException("Cannot updating address without id");
    }

    @Override
    public Optional<Address> getById(long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Address> getByTextAddress(String address) {
        log.debug("IN getByTextAddress -> find address: {}", address);
        var addressOpt = repository.findByTextAddress(address);

        if (addressOpt.isEmpty()) {
            log.info("IN getByTextAddress -> not found registered address: {}", address);
        } else {
            log.info("IN getByTextAddress -> was found registered address: {}", address);
        }

        return addressOpt;
    }

    private Address createTextAddress(Address address) {
        if (address.getCountry() == null || address.getRegion() == null || address.getLocality() == null) {
            log.error("IN registry -> filled not all required fields for registry address");
            throw new IllegalArgumentException("Filled not all required fields for registry address");
        }
        loadCountry(address);
        loadRegion(address);
        loadLocality(address);

        checkRegionConsistency(address);
        checkLocalityConsistency(address);

        String textAddress = address.getCountry().getName()+", "+address.getRegion().getName() +", "+address.getLocality().getName()+" "+address.getLocality().getType().getDescription();
        address.setTextAddress(textAddress);
        return address;
    }

    private Address loadCountry(Address address) {
        var country = address.getCountry();
        if (country.getId() != null && country.getName() != null && country.getCode() != null) {
            return address;
        } else if (country.getId() != null || (country.getName() != null && country.getCode() != null)) {
            return address.setCountry(countryService.saveOrLoad(country));
        } else {
            log.error("IN registry -> country from address cannot load or save: {}", country);
            throw new IllegalArgumentException("Country from address cannot load or save: " + country);
        }
    }

    private Address loadRegion(Address address) {
        var region = address.getRegion();
        if (region.getCountry() == null || (region.getCountry().equals(address.getCountry()) && region.getCountry().getId() == null))
            region.setCountry(address.getCountry());

        if (region.getId() != null && region.getName() != null && region.getCode() != null) {
            return address;
        } else if (region.getId() != null || (region.getName() != null && region.getCode() != null)) {
            return address.setRegion(regionService.saveOrLoad(region));
        } else {
            log.error("IN registry -> region from address cannot load or save: {}", region);
            throw new IllegalArgumentException("Region from address cannot load or save: " + region);
        }
    }

    private Address loadLocality(Address address) {
        var locality = address.getLocality();
        if (locality.getRegion() == null || (locality.getRegion().equals(address.getRegion()) && locality.getRegion().getId() == null))
            locality.setRegion(address.getRegion());

        if (locality.getId() != null && locality.getName() != null && locality.getType() != null) {
            return address;
        } else if (locality.getId() != null || (locality.getName() != null && locality.getType() != null && locality.getRegion() != null && locality.getRegion().getId() != null)) {
            return address.setLocality(localityService.saveOrLoad(locality));
        } else {
            log.error("IN registry -> locality from address cannot load or save: {}", locality);
            throw new IllegalArgumentException("Locality from address cannot load or save: " + locality);
        }
    }

    private void checkRegionConsistency(Address address) {
        if (!address.getRegion().getCountry().equals(address.getCountry())){
            log.error("IN registry -> region country not equals address country");
            throw new IllegalArgumentException("Region country not equals address country");
        }
    }

    private void checkLocalityConsistency(Address address) {
        if (!address.getLocality().getRegion().equals(address.getRegion())){
            log.error("IN registry -> locality region not equals address region");
            throw new IllegalArgumentException("Region country not equals address country");
        }
    }
}
