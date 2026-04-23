package com.resonance.profile.service;

/**
 * CN: 这里先只拿微信登录闭环真正需要的 openid / unionid / sessionKey，不把无关字段拖进来。
 * EN: Only keep the fields the current WeChat login loop actually needs: openid, unionid, and sessionKey. Do not drag in irrelevant payload.
 * JP: ここでは現在の WeChat ログイン経路で本当に必要な openid / unionid / sessionKey だけを保持し、不要なフィールドを持ち込みません。
 */
public record WechatMiniappSession(
        String openId,
        String unionId,
        String sessionKey) {
}