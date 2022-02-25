package org.brutforcer.service.user.service.impl;

import org.brutforcer.service.user.entity.Address;
import org.brutforcer.service.user.repository.AddressRepository;
import org.brutforcer.service.user.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaAddressService implements AddressService {

    private final AddressRepository repository;

    public JpaAddressService(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Address add(Address address) {
        return repository.saveAndFlush(address);
    }

    @Override
    public Address update(Address address) {
        return repository.saveAndFlush(address);
    }

    @Override
    public Optional<Address> getById(long id) {
        return repository.findById(id);
    }
}
