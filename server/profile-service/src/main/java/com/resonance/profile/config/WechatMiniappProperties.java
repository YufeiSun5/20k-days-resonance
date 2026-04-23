package com.resonance.profile.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wechat.miniapp")
/**
 * CN: 微信小程序登录配置只放服务端，前端永远不接触 AppSecret。少讲故事，这就是基本安全边界。
 * EN: WeChat Mini Program login configuration lives on the server only. The frontend must never touch the AppSecret. This is the basic security boundary.
 * JP: WeChat ミニプログラムのログイン設定はサーバ側だけに置きます。フロントエンドは AppSecret に触れません。これが基本的なセキュリティ境界です。
 */
public record WechatMiniappProperties(
        String appId,
        String appSecret,
        String sessionUrl) {

    public String resolvedSessionUrl() {
        if (sessionUrl == null || sessionUrl.isBlank()) {
            return "https://api.weixin.qq.com/sns/jscode2session";
        }
        return sessionUrl;
    }

    public boolean configured() {
        return appId != null && !appId.isBlank() && appSecret != null && !appSecret.isBlank();
    }
}