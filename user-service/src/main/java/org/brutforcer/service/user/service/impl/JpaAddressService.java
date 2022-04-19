package org.brutforcer.service.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.brutforcer.service.user.entity.Address;
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
        log.debug("IN registry -> creating new address: {}", address.toString());
        if (address.getTextAddress() == null) {
            setTextAddress(address);
        }
        if (address.getTextAddress() == null) {
            log.error("IN registry -> filled not all required fields for registry address");
            throw new IllegalArgumentException("Filled not all required fields for registry address");
        }

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

    private Address setTextAddress(Address address) {
        if (address.getCountry() == null || address.getRegion() == null || address.getLocality() == null) {
            return address;
        }
        String textAddress = address.getCountry().getName()+", "+address.getRegion().getName() +", "+address.getLocality().getName()+" "+address.getLocality().getType().getDescription();
        address.setTextAddress(textAddress);
        return address;
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

    public Optional<Address> getByCountryIdAndRegionIdAndLocalityId(long countryId, long regionId, long localityId){
        return Optional.empty();
    }
}
