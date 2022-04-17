package org.brutforcer.service.user.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Schema(description = "Адрес")
@Data
@Accessors(chain = true)
@Entity
@NoArgsConstructor
@Table(name = "addresses")
public class Address extends BaseEntity {

    @Schema(description = "Страна")
    @OneToOne(optional = false, targetEntity = Country.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Schema(description = "Регион")
    @OneToOne(targetEntity = Region.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "region_id")
    private Region region;

    @Schema(description = "Населенный пункт")
    @OneToOne(optional = false, targetEntity = Locality.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "locality_id", nullable = false)
    private Locality locality;

    @Override
    public String toString() {
        return "Address: " + country.getName() + ", " + region.getName() + ", " + locality.getName();
    }
}
