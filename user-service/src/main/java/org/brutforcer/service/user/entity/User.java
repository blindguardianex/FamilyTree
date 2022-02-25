package org.brutforcer.service.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Data
@Accessors(chain = true)
@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @NotBlank
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Embedded
    private UserProfile profile;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    public void addRole(Role role){
        roles.add(role);
    }

    @Override
    public String toString() {
        return "User: " + username +
                ", \npassword: " + password +
                ", \nprofile: " + profile;
    }
}
