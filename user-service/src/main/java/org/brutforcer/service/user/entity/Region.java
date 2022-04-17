package org.brutforcer.service.user.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "Регион")
@Data
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
    @ManyToOne(optional = false, targetEntity = Country.class)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Schema(hidden = true)
    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
    private List<Locality> localities = new ArrayList<>();

    @Override
    public String toString() {
        return "Region{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", country=" + country +
                '}';
    }
}
