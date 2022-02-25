package org.brutforcer.service.user.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "addresses")
public class Address extends BaseEntity{

    @OneToOne(optional = false, targetEntity = Country.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToOne(targetEntity = Region.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToOne(optional = false, targetEntity = Locality.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "locality_id", nullable = false)
    private Locality locality;

    @Override
    public String toString() {
        return "country: " + country +
                ", \nregion: " + region +
                ", \nlocality: " + locality;
    }
}
