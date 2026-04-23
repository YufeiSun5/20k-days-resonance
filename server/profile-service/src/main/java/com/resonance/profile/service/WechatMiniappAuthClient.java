package com.resonance.profile.service;

public interface WechatMiniappAuthClient {

    WechatMiniappSession resolveSession(String code);
}