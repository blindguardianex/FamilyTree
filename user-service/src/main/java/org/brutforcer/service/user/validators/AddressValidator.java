package org.brutforcer.service.user.validators;

import lombok.extern.slf4j.Slf4j;
import org.brutforcer.service.user.entity.Address;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AddressValidator {

    public boolean validate(Address address) {
        checkNonNullFields(address);
        checkCountryConsistency(address);
        checkRegionConsistency(address);
        return true;
    }

    private void checkNonNullFields(Address address) {
        if (address.getCountry() == null)
            throw new IllegalArgumentException("Address country is null");
        if (address.getRegion() == null)
            throw new IllegalArgumentException("Address region is null");
        if (address.getLocality() == null)
            throw new IllegalArgumentException("Address locality is null");
        if (address.getRegion().getCountry() == null)
            throw new IllegalArgumentException("Region has null country");
        if (address.getLocality().getRegion() == null)
            throw new IllegalArgumentException("Locality has null region");
    }

    private void checkCountryConsistency(Address address) {
        if (!address.getRegion().getCountry().equals(address.getCountry())) {
            log.error("Address country not equals region country. Address country: {}, region country: {}", address.getCountry(), address.getRegion().getCountry());
            throw new IllegalArgumentException("Address country not equals region country");
        }
    }

    private void checkRegionConsistency(Address address) {
        if (!address.getLocality().getRegion().equals(address.getRegion())) {
            log.error("Address region not equals locality region. Address region: {}, locality region: {}", address.getRegion(), address.getLocality().getRegion());
            throw new IllegalArgumentException("Address region not equals locality region");
        }
    }
}
