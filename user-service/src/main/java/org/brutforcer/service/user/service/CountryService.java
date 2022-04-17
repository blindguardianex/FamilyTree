package org.brutforcer.service.user.service;

import org.brutforcer.service.user.entity.Country;
import org.brutforcer.service.user.entity.Locality;

public interface CountryService {

    Country saveOrLoad(Country country);
}
