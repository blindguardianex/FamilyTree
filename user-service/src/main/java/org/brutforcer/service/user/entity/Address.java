package org.brutforcer.service.user.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Address extends BaseEntity{

    @OneToOne(optional = false, targetEntity = Country.class)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToOne(targetEntity = Region.class)
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToOne(optional = false, targetEntity = Locality.class)
    @JoinColumn(name = "locality_id", nullable = false)
    private Locality locality;
}
