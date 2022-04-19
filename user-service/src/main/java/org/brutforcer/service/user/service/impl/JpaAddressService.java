package org.brutforcer.service.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.brutforcer.service.user.entity.Address;
import org.brutforcer.service.user.entity.Role;
import org.brutforcer.service.user.repository.AddressRepository;
import org.brutforcer.service.user.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class JpaAddressService implements AddressService {

    private final AddressRepository repository;

    public JpaAddressService(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Address registry(Address address) {
        log.debug("IN add -> creating new address: {}", address.toString());
        Optional<Address> addressOpt = this.getByCountryIdAndRegionIdAndLocalityId(address.getCountry().getId(), address.getRegion().getId(), address.getLocality().getId());
        return addressOpt.map(existing -> {
                    log.warn("IN add -> adress \"{}\" already exist with id {}", existing, existing.getId());
                    return existing;
                })
                .orElseGet(() -> {
                    Address saved = repository.saveAndFlush(address);
                    log.info("IN add -> address \"{}\" successfully created", saved);
                    return saved;
                });
    }

    @Override
    public Address update(Address address) {
        return repository.saveAndFlush(address);
    }

    @Override
    public Address upsert(Address address) {
        return null;
    }

    @Override
    public Optional<Address> getById(long id) {
        return repository.findById(id);
    }

    public Optional<Address> getByCountryIdAndRegionIdAndLocalityId(long countryId, long regionId, long localityId){
        return Optional.empty();
    }
}
