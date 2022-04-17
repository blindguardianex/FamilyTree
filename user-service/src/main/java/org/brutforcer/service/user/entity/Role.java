package org.brutforcer.service.user.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Accessors(chain = true)
@Entity
@NoArgsConstructor
@Table(name = "roles")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseEntity{

    @NotBlank
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
