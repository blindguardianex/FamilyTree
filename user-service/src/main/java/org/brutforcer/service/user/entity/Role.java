package org.brutforcer.service.user.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;


@Data
@Accessors(chain = true)
@Entity
public class Role extends BaseEntity{

    @NotBlank
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
