package org.brutforcer.service.user.service;

import org.brutforcer.service.user.entity.Region;

import java.util.Optional;

public interface RegionService {

    Region saveOrLoad(Region region);
    void delete(Region region);
}
