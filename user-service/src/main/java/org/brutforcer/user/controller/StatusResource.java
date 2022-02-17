package org.brutforcer.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(
        name = "status-api",
        description = "API статуса приложения")
public interface StatusResource {


    @Operation(summary = "Получение статуса приложения")
    @GetMapping("/api/v1/healthStatus")
    ResponseEntity<String> status();
}