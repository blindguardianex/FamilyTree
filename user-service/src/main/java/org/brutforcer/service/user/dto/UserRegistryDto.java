package org.brutforcer.service.user.dto;

import org.brutforcer.service.user.entity.UserProfile;

public record UserRegistryDto(
        String login,
        String password,
        String firstName,
        String lastName,
        String otherName,
        String email,
        String phoneNumber) {
}
