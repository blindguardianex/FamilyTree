package org.brutforcer.service.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.brutforcer.service.user.entity.Address;
import org.brutforcer.service.user.entity.User;
import org.brutforcer.service.user.entity.UserProfile;

import javax.validation.constraints.*;
import java.time.LocalDate;

//todo: add address to attributes
public record UserRegistryDto(
        @NotBlank
        String username,
        @NotBlank
        @Size(min = 6, message = "Минимальная длинна пароля - 6 символов")
        String password,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank
        String otherName,
        @NotBlank
        @Email(message = "Введите корректный адрес электронной почты")
        String email,
        @NotBlank
        @Pattern(regexp = "^(\\+)?((\\d{2,3}) ?\\d|\\d)(([ -]?\\d)|( ?(\\d{2,3}) ?)){5,12}\\d$", message = "Введите корректный номер телефона")
        String phoneNumber,
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate birthDate) {

    public User toUser() {
        return new User()
                .setUsername(username)
                .setPassword(password)
                .setProfile(
                        new UserProfile()
                                .setFirstName(firstName)
                                .setLastName(lastName)
                                .setOtherName(otherName)
                                .setEmail(email)
                                .setPhoneNumber(phoneNumber)
                                .setBirthDate(birthDate)
                );
    }
}
