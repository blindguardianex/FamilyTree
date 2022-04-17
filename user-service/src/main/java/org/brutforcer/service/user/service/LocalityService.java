package org.brutforcer.service.user.service;

import org.brutforcer.service.user.entity.Locality;
import org.brutforcer.service.user.entity.Region;

public interface LocalityService {

    Locality saveOrLoad(Locality locality);
    void delete(Locality locality);
}
