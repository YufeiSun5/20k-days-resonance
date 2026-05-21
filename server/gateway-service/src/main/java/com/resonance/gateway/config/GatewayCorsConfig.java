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
        // CN: Web 版生产页会带 Origin 做 JSON/SSE 预检；小程序不会，所以这里必须同时放开正式域名和本地开发来源。
        // EN: The production web page sends Origin preflights for JSON/SSE; the mini program does not, so allow both production domains and local dev origins.
        // JP: Web 版の本番ページは JSON/SSE で Origin 付きプリフライトを送ります。ミニプログラムは送らないため、本番ドメインとローカル開発元の両方を許可します。
        registry.addMapping("/api/**")
                .allowedOriginPatterns(
                        "https://sunyufei5.art",
                        "https://www.sunyufei5.art",
                        "http://127.0.0.1:*",
                        "http://localhost:*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600);
    }
}
