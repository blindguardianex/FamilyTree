package org.brutforcer.service.user.service.impl;

import org.apache.tomcat.jni.Address;
import org.brutforcer.service.user.repository.AddressRepository;
import org.brutforcer.service.user.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaAddressService implements AddressService {

    private final AddressRepository addressRepository;

    public JpaAddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address add(Address address) {
        return addressRepository.saveAndFlush(address);
    }

    @Override
    public Address update(Address address) {
        return addressRepository.saveAndFlush(address);
    }

    @Override
    public Optional<Address> getById(Long id) {
        return addressRepository.findById(id);
    }
}
