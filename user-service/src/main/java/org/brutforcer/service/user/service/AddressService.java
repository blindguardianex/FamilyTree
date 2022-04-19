package org.brutforcer.service.user.service;


import org.brutforcer.service.user.entity.Address;

import java.util.Optional;

public interface AddressService {

    Address registry(Address address);
    Address update(Address address);
    Address upsert(Address address);
    Optional<Address> getById(long id);
}
