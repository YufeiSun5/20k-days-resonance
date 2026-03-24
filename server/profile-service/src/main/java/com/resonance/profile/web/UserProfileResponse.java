package com.resonance.profile.web;

import com.resonance.profile.entity.ProfileUser;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record UserProfileResponse(
        UUID id,
        String displayName,
        String avatarUrl,
        LocalDate birthDate,
        String locale,
        String status,
        Instant createdAt,
        Instant updatedAt) {

    public static UserProfileResponse from(ProfileUser user) {
        return new UserProfileResponse(
                user.getId(),
                user.getDisplayName(),
                user.getAvatarUrl(),
                user.getBirthDate(),
                user.getLocale(),
                user.getStatus().name(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }
}