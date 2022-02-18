package org.brutforcer.service.user.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Embeddable
public class UserProfile {

    @NotBlank
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank
    @Column(name = "other_name", nullable = false)
    private String otherName;

    @NotBlank
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank
    @Pattern(regexp = "^(\\+)?((\\d{2,3}) ?\\d|\\d)(([ -]?\\d)|( ?(\\d{2,3}) ?)){5,12}\\d$")
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @OneToOne(optional = false, targetEntity = Address.class)
    @JoinColumn(name = "birth_place", insertable = true)
    private Address birthPlace;

    @Column(name = "death_date", nullable = false)
    private LocalDate deathDate;

    @OneToOne(targetEntity = Address.class)
    @JoinColumn(name = "death_place", insertable = true)
    private Address deathPlace;
}
