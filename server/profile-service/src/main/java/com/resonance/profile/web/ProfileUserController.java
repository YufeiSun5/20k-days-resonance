package com.resonance.profile.web;

import com.resonance.profile.entity.ProfileUser;
import com.resonance.profile.service.ProfileUserService;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/profile/users")
/**
 * CN: profile 对外先暴露最小用户接口，优先服务前端联调，而不是一开始就堆完整认证协议。
 * EN: The profile controller exposes only the minimum user-facing endpoints first, prioritizing frontend integration over premature full authentication design.
 * JP: profile コントローラーはまず最小限のユーザー API だけを公開し、最初から完全な認証仕様を積み上げるよりもフロント連携を優先します。
 */
public class ProfileUserController {

    private final ProfileUserService profileUserService;

    public ProfileUserController(ProfileUserService profileUserService) {
        this.profileUserService = profileUserService;
    }

    @PostMapping("/mock-login")
    /**
     * CN: 给小程序和调试工具一个稳定入口，先换到可用 userId，再谈真实登录。
     * EN: This provides a stable entry point for the mini program and debug tools: get a usable userId first, then evolve toward real login.
     * JP: ミニプログラムとデバッグツール向けの安定した入口です。まず使える userId を得て、その後で本物のログインに進みます。
     */
    public UserProfileResponse mockLogin(@RequestBody MockLoginRequest request) {
        ProfileUser user = profileUserService.mockLogin(request);
        return UserProfileResponse.from(user);
    }

    @GetMapping("/{userId}")
    /**
     * CN: 明确按 userId 读取，避免在接口层偷偷引入“当前用户”假设。
     * EN: Read by explicit userId so the API does not silently depend on an implicit current-user assumption.
     * JP: 明示的な userId で取得し、API 層に暗黙の「現在ユーザー」前提を持ち込まないようにします。
     */
    public UserProfileResponse getUser(@PathVariable UUID userId) {
        return UserProfileResponse.from(profileUserService.getUser(userId));
    }
}