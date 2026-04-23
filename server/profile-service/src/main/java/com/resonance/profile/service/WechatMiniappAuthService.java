package com.resonance.profile.service;

import com.resonance.profile.config.WechatMiniappProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

@Component
/**
 * CN: 这里负责用 code 向微信换 openid。这个调用失败时要明确炸掉，别伪造“登录成功”把脏状态写进库里。
 * EN: This component exchanges the login code for openid from WeChat. If the call fails, it must fail loudly instead of faking a login success and writing dirty state.
 * JP: ここは code を使って WeChat から openid を取得します。失敗時は明確に落とし、中途半端なログイン成功を偽装して汚れた状態を保存してはいけません。
 */
public class WechatMiniappAuthService implements WechatMiniappAuthClient {

    private final RestClient restClient;
    private final WechatMiniappProperties properties;

    public WechatMiniappAuthService(WechatMiniappProperties properties) {
        this.properties = properties;
        this.restClient = RestClient.builder().build();
    }

    @Override
    public WechatMiniappSession resolveSession(String code) {
        if (code == null || code.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "wechat login code is required");
        }
        if (!properties.configured()) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "wechat miniapp config missing");
        }

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("appid", properties.appId());
        params.add("secret", properties.appSecret());
        params.add("js_code", code.trim());
        params.add("grant_type", "authorization_code");

        String requestUrl = UriComponentsBuilder.fromUriString(properties.resolvedSessionUrl())
            .queryParams(params)
            .build(true)
            .toUriString();

        WechatSessionResponse response = restClient.get()
            .uri(requestUrl)
                .retrieve()
                .body(WechatSessionResponse.class);

        if (response == null) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "wechat session response missing");
        }
        if (response.errcode() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                    "wechat session exchange failed: " + response.errcode() + " " + response.errmsg());
        }
        if (response.openid() == null || response.openid().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "wechat openid missing");
        }

        return new WechatMiniappSession(response.openid(), response.unionid(), response.session_key());
    }

    private record WechatSessionResponse(
            String openid,
            String unionid,
            String session_key,
            Integer errcode,
            String errmsg) {
    }
}