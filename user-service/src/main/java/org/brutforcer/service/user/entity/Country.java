package org.brutforcer.service.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.HashCodeExclude;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Schema(description = "Страна")
@Setter
@Getter
@Accessors(chain = true)
@Entity
@NoArgsConstructor
@Table(name = "countries")
public class Country extends BaseEntity{

    @Schema(description = "Наименование страны", example = "Российская Федерация")
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Schema(description = "Код страны", example = "RUS")
    @NotBlank
    @Column(name = "code", nullable = false)
    private String code;

    @Schema(hidden = true)
    @JsonIgnore
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Region> regions = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name) && Objects.equals(code, country.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, code);
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
