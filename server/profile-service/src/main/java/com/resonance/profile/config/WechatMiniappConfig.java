package com.resonance.profile.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(WechatMiniappProperties.class)
public class WechatMiniappConfig {
}