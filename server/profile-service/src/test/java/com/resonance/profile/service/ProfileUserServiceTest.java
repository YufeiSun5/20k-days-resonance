package com.resonance.profile.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.resonance.profile.domain.AuthProvider;
import com.resonance.profile.entity.AuthIdentity;
import com.resonance.profile.entity.ProfileUser;
import com.resonance.profile.repository.AuthIdentityRepository;
import com.resonance.profile.repository.ProfileUserRepository;
import com.resonance.profile.web.MockLoginRequest;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class ProfileUserServiceTest {

    @Mock
    private ProfileUserRepository userRepository;

    @Mock
    private AuthIdentityRepository authIdentityRepository;

    @InjectMocks
    private ProfileUserService profileUserService;

    @Test
    void mockLoginReusesExistingWechatIdentity() {
        ProfileUser existingUser = new ProfileUser();
        existingUser.setId(UUID.randomUUID());
        existingUser.setDisplayName("Existing User");

        AuthIdentity existingIdentity = new AuthIdentity();
        existingIdentity.setUser(existingUser);

        when(authIdentityRepository.findByProviderAndProviderUserId(AuthProvider.wechat_miniapp, "openid-1"))
                .thenReturn(Optional.of(existingIdentity));

        MockLoginRequest request = new MockLoginRequest(
                "Sun",
                null,
                LocalDate.of(2000, 1, 1),
                "zh-CN",
                AuthProvider.wechat_miniapp,
                "openid-1",
                null,
                null);

        ProfileUser result = profileUserService.mockLogin(request);

        assertSame(existingUser, result);
        verify(userRepository, never()).save(any(ProfileUser.class));
        verify(authIdentityRepository, never()).save(any(AuthIdentity.class));
    }

    @Test
    void mockLoginCreatesUserAndIdentityWhenNoExistingIdentity() {
        when(userRepository.save(any(ProfileUser.class))).thenAnswer(invocation -> {
            ProfileUser user = invocation.getArgument(0);
            user.setId(UUID.randomUUID());
            return user;
        });
        when(authIdentityRepository.save(any(AuthIdentity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        MockLoginRequest request = new MockLoginRequest(
                "Sun",
                null,
                null,
                "zh-CN",
                AuthProvider.wechat_miniapp,
                null,
                null,
                null);

        ProfileUser result = profileUserService.mockLogin(request);

        assertEquals("Sun", result.getDisplayName());
        ArgumentCaptor<AuthIdentity> identityCaptor = ArgumentCaptor.forClass(AuthIdentity.class);
        verify(authIdentityRepository).save(identityCaptor.capture());
        assertEquals(AuthProvider.wechat_miniapp, identityCaptor.getValue().getProvider());
        assertTrue(identityCaptor.getValue().getProviderUserId().startsWith("mock-openid-"));
    }

    @Test
    void getUserThrows404WhenUserMissing() {
        UUID userId = UUID.randomUUID();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> profileUserService.getUser(userId));
    }
}