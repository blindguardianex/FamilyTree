package org.brutforcer.service.user.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.brutforcer.service.user.enums.Sex;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;


@Data
@Accessors(chain = true)
@Embeddable
@NoArgsConstructor
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
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Sex sex;

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

    @NotNull
    @OneToOne(optional = false, targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "birth_place", insertable = true)
    private Address birthPlace;

    @Override
    public String toString() {
        return  "firstName: " + firstName + ", lastName: " + lastName + ", otherName: " + otherName + ", email: " + email + ", phoneNumber:  " + phoneNumber + ", birthDate: " + birthDate + ",\n" +
                "birthPlace: " + birthPlace;
    }
}
