package com.resonance.profile.service;

import com.resonance.profile.domain.AuthProvider;
import com.resonance.profile.entity.AuthIdentity;
import com.resonance.profile.entity.ProfileUser;
import com.resonance.profile.repository.AuthIdentityRepository;
import com.resonance.profile.repository.ProfileUserRepository;
import com.resonance.profile.web.MockLoginRequest;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
/**
 * CN: 用户服务先只做最小闭环，不碰复杂认证，把前端联调用的 mock 登录和用户读取稳定下来。
 * EN: This user service intentionally keeps the first iteration narrow: stabilize mock login and user lookup for frontend integration before real auth complexity is introduced.
 * JP: このユーザーサービスは初期段階では最小閉ループだけを扱います。複雑な認証には踏み込まず、まずフロント連携用の mock ログインとユーザー取得を安定させます。
 */
public class ProfileUserService {

    private final ProfileUserRepository userRepository;
    private final AuthIdentityRepository authIdentityRepository;

    public ProfileUserService(ProfileUserRepository userRepository, AuthIdentityRepository authIdentityRepository) {
        this.userRepository = userRepository;
        this.authIdentityRepository = authIdentityRepository;
    }

    @Transactional
    /**
     * CN: mockLogin 的目标不是伪造完整认证系统，而是先稳定地产生一个可复用用户，让前后端联调能继续走。
     * EN: mockLogin does not pretend to be a full authentication system. Its job is to produce a reusable user record so frontend-backend integration can move forward.
     * JP: mockLogin の目的は完全な認証基盤を偽装することではなく、再利用可能なユーザーを安定して作り、フロントとバックエンドの連携を前に進めることです。
     */
    public ProfileUser mockLogin(MockLoginRequest request) {
        AuthProvider provider = request.provider();
        if (provider == AuthProvider.wechat_miniapp && request.providerUserId() != null && !request.providerUserId().isBlank()) {
            return authIdentityRepository.findByProviderAndProviderUserId(provider, request.providerUserId())
                    .map(AuthIdentity::getUser)
                    .orElseGet(() -> createUserWithIdentity(request));
        }
        return createUserWithIdentity(request);
    }

    @Transactional(readOnly = true)
    /**
     * CN: 用户读取保持简单直接，找不到就明确返回 404，不做模糊降级。
     * EN: User lookup stays blunt and explicit: if the record is absent, return 404 instead of hiding the error behind a fuzzy fallback.
     * JP: ユーザー取得は単純明快に保ちます。見つからなければ曖昧なフォールバックをせず、明示的に 404 を返します。
     */
    public ProfileUser getUser(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
    }

    private ProfileUser createUserWithIdentity(MockLoginRequest request) {
        // CN: 这里一次性落用户主体和身份绑定，避免生成“有用户没身份”这种半残状态。
        // EN: Persist the user and its identity in one transaction so we do not leave behind half-created account state.
        // JP: ユーザー主体と認証識別子を一つのトランザクションで保存し、中途半端なアカウント状態を残さないようにします。
        ProfileUser user = new ProfileUser();
        user.setDisplayName(resolveDisplayName(request));
        user.setAvatarUrl(request.avatarUrl());
        user.setBirthDate(request.birthDate());
        if (request.locale() != null && !request.locale().isBlank()) {
            user.setLocale(request.locale());
        }
        ProfileUser savedUser = userRepository.save(user);

        AuthIdentity identity = new AuthIdentity();
        identity.setUser(savedUser);
        identity.setProvider(request.provider());
        identity.setProviderUserId(resolveProviderUserId(request));
        identity.setEmail(request.email());
        identity.setPasswordHash(request.passwordHash());
        authIdentityRepository.save(identity);
        return savedUser;
    }

    private String resolveDisplayName(MockLoginRequest request) {
        if (request.displayName() != null && !request.displayName().isBlank()) {
            return request.displayName();
        }
        return "Resonance User";
    }

    private String resolveProviderUserId(MockLoginRequest request) {
        if (request.providerUserId() != null && !request.providerUserId().isBlank()) {
            return request.providerUserId();
        }
        if (request.provider() == AuthProvider.wechat_miniapp) {
            return "mock-openid-" + UUID.randomUUID();
        }
        return null;
    }
}