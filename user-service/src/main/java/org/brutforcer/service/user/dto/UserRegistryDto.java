package org.brutforcer.service.user.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.brutforcer.service.user.entity.Address;
import org.brutforcer.service.user.entity.User;
import org.brutforcer.service.user.entity.UserProfile;

import javax.validation.constraints.*;
import java.time.LocalDate;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UserRegistryDto(
        @NotBlank(message = "Введите логин")
        @Size(min = 4, max = 32, message = "Минимальная длинна логина - 4 символа, максимальная - 32")
        String username,
        @NotBlank(message = "Введите пароль")
        @Size(min = 6, max = 32, message = "Минимальная длинна пароля - 6 символов, максимальная - 32")
        String password,
        @NotBlank(message = "Введите имя")
        String firstName,
        @NotBlank(message = "Введите фамилию")
        String lastName,
        @NotBlank(message = "Введите отчество")
        String otherName,
        @NotBlank(message = "Введите адрес электронной почты")
        @Email(message = "Введите корректный адрес электронной почты")
        String email,
        @Pattern(regexp = "^(\\+)?((\\d{2,3}) ?\\d|\\d)(([ -]?\\d)|( ?(\\d{2,3}) ?)){5,12}\\d$", message = "Введите корректный номер телефона")
        String phoneNumber,
        @NotNull
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate birthDate,
        @NotNull
        Address birthPlace) {

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
                                .setBirthPlace(birthPlace)
                );
    }
}
