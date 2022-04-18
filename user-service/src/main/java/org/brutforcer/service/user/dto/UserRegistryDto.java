package org.brutforcer.service.user.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import org.brutforcer.service.user.entity.Address;
import org.brutforcer.service.user.entity.User;
import org.brutforcer.service.user.entity.UserProfile;
import org.brutforcer.service.user.enums.Sex;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Schema(description = "Сущность регистрации пользователя")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UserRegistryDto(

        @Schema(description = "Логин", example = "ferZ")
        @NotBlank(message = "Введите логин")
        @Size(min = 4, max = 32, message = "Минимальная длинна логина - 4 символа, максимальная - 32")
        String username,

        @Schema(description = "Пароль", example = "password")
        @NotBlank(message = "Введите пароль")
        @Size(min = 6, max = 32, message = "Минимальная длинна пароля - 6 символов, максимальная - 32")
        String password,

        @Schema(description = "Имя", example = "Николай")
        @NotBlank(message = "Введите имя")
        String firstName,

        @Schema(description = "Фамилия", example = "Ставрогин")
        @NotBlank(message = "Введите фамилию")
        String lastName,

        @Schema(description = "Отчество", example = "Всеволодович")
        @NotBlank(message = "Введите отчество")
        String otherName,

        @Schema(description = "Пол", example = "MALE")
        @NotBlank(message = "Укажите пол")
        Sex sex,

        @Schema(description = "Адрес электронной почты", example = "ferz@mail.ru")
        @NotBlank(message = "Введите адрес электронной почты")
        @Email(message = "Введите корректный адрес электронной почты")
        String email,

        @Schema(description = "Телефонный номер", example = "8(800)555-35-35")
        @Pattern(regexp = "^(\\+)?((\\d{2,3}) ?\\d|\\d)(([ -]?\\d)|( ?(\\d{2,3}) ?)){5,12}\\d$", message = "Введите корректный номер телефона")
        String phoneNumber,

        @Schema(description = "Дата рождения", example = "1994-11-11")
        @NotNull
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate birthDate,

        @Schema(description = "Место рождения")
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
                                .setSex(sex)
                                .setEmail(email)
                                .setPhoneNumber(phoneNumber)
                                .setBirthDate(birthDate)
                                .setBirthPlace(birthPlace)
                );
    }
}
