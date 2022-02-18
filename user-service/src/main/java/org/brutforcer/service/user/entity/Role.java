package org.brutforcer.service.user.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Role extends BaseEntity{

    @NotBlank
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
