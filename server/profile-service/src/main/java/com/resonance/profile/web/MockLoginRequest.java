package com.resonance.profile.web;

import com.resonance.profile.domain.AuthProvider;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public record MockLoginRequest(
        String displayName,
        String avatarUrl,
        LocalDate birthDate,
        String locale,
        AuthProvider provider,
        String providerUserId,
        String email,
        String passwordHash) {

    public MockLoginRequest {
        if (provider == null) {
            provider = AuthProvider.wechat_miniapp;
        }
    }
}