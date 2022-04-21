package org.brutforcer.service.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Schema(description = "Регион")
@Setter
@Getter
@Accessors(chain = true)
@Entity
@NoArgsConstructor
@Table(name = "regions")
public class Region extends BaseEntity{

    @Schema(description = "Наименование региона", example = "Тульская область")
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Schema(description = "Код региона", example = "71")
    @NotBlank
    @Column(name = "code", nullable = false)
    private String code;

    @Schema(hidden = true)
    @NotNull
    @JsonIgnore
    @ManyToOne(optional = false, targetEntity = Country.class)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Schema(hidden = true)
    @JsonIgnore
    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
    private List<Locality> localities = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return Objects.equals(name, region.name) && Objects.equals(code, region.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, code);
    }

    @Override
    public String toString() {
        return "Region{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", country=" + country +
                '}';
    }
}
