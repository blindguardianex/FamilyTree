package org.brutforcer.service.user.service;

import org.apache.tomcat.jni.Address;

import java.util.Optional;

public interface AddressService {

    Address add(Address address);
    Address update(Address address);
    Optional<Address> getById(Long id);
}
