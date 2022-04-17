package org.brutforcer.service.user.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "Страна")
@Data
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
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Region> regions = new ArrayList<>();

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
