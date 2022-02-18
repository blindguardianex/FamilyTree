package org.brutforcer.service.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.brutforcer.service.user.dto.UserRegistryDto;
import org.brutforcer.service.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(
        name = "user-management",
        description = "Управление пользователями")
@RequestMapping("/api/v1/user-management")
public interface UserManagementResource {

    @Operation(summary = "Зарегистрировать пользователя")
    @PostMapping("/user")
    ResponseEntity<User> signUp(UserRegistryDto registryDto);

    @Operation(summary = "Получить профиль пользователя по id")
    @GetMapping("/user/{userId}}")
    ResponseEntity<User> profileById(@PathVariable("userId") long userId);
}
