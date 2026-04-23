package com.resonance.profile.web;

import jakarta.validation.constraints.NotBlank;

public record WechatLoginRequest(
        @NotBlank String code,
        String locale) {
}