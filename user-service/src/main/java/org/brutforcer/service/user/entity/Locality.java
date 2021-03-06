package org.brutforcer.service.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.brutforcer.service.user.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "Населенный пункт")
@Data
@Accessors(chain = true)
@Entity
@NoArgsConstructor
@Table(name = "localities")
public class Locality extends BaseEntity{

    @Schema(description = "Наименование населенного пункта", example = "Тула")
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Schema(description = "Тип населенного пункта", example = "CITY")
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @Schema(hidden = true)
    @NotNull
    @JsonIgnore
    @ManyToOne(optional = false, targetEntity = Region.class)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @Getter
    public static enum Type{
        SETTLEMENT("Поселок городского типа"),
        COUNTRY("Деревня"),
        CITY("Город");

        private final String description;

        Type(String description) {
            this.description = description;
        }
    }



    @Override
    public String toString() {
        return "Locality{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", region=" + region +
                '}';
    }
}
