package org.brutforcer.service.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.brutforcer.service.user.dto.UserRegistryDto;
import org.brutforcer.service.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(
        name = "user-management",
        description = "Управление пользователями")
@RequestMapping("/api/v1/user-management")
public interface UserManagementResource {

    @CrossOrigin
    @Operation(summary = "Зарегистрировать пользователя")
    @PostMapping("/user")
    ResponseEntity<User> signUp(@RequestBody @Valid UserRegistryDto registryDto);

    @Operation(summary = "Получить профиль пользователя по id")
    @GetMapping("/user/{userId}")
    ResponseEntity<User> profileById(@PathVariable("userId") long userId);

    @Operation(summary = "Получить профиль пользователя по логину")
    @GetMapping("/user/{username}")
    ResponseEntity<User> profileByUsername(@PathVariable("username") String username);
}
