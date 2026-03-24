package com.resonance.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
/**
 * CN: 这里先只解决本地前端联调跨域，不把生产期安全策略硬编码进来。现在的目标很单纯：让 H5 调试页能稳定打到 gateway。
 * EN: This config solves local frontend integration CORS first and does not hardcode production security policy yet. The immediate goal is simple: let the H5 debug page reach the gateway reliably.
 * JP: ここではまずローカルのフロント連携用 CORS だけを解決し、本番向けの安全方針を今ここで固定しません。目標は単純で、H5 デバッグ画面が gateway に安定して届くことです。
 */
public class GatewayCorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        // CN: 只放开本地开发来源，够联调，不要脑子一热直接 '*'。
        // EN: Open only local development origins. That is enough for integration; do not lazily drop a global '*'.
        // JP: ローカル開発オリジンだけを許可します。連携には十分で、安易に全体 '*' を開けるべきではありません。
        registry.addMapping("/api/**")
                .allowedOriginPatterns("http://127.0.0.1:*", "http://localhost:*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600);
    }
}