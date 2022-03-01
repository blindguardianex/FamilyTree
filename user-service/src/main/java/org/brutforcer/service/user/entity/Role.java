package org.brutforcer.service.user.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Data
@Accessors(chain = true)
@Entity
@NoArgsConstructor
@Table(name = "roles")
public class Role extends BaseEntity{

    @NotBlank
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
