package org.brutforcer.service.user.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.brutforcer.service.user.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@Entity
public class Locality extends BaseEntity{

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @OneToOne(targetEntity = Address.class)
    @JoinColumn(name = "region_id", insertable = false, updatable = false)
    private Region region;

    public static enum Type{
        SETTLEMENT("Поселок городского типа"),
        COUNTRY("Деревня"),
        CITY("Город");

        private final String description;

        Type(String description) {
            this.description = description;
        }
    }
}
