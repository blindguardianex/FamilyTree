package org.brutforcer.service.user.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Region extends BaseEntity{

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "code", nullable = false)
    private String code;

    @OneToOne(optional = false, targetEntity = Country.class)
    @JoinColumn(name = "country_id", insertable = false, updatable = false, nullable = false)
    private Country country;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "region_localities",
            joinColumns = @JoinColumn(name = "region_id"),
            inverseJoinColumns = @JoinColumn(name = "locality_id")
    )
    private List<Locality> localities;
}
