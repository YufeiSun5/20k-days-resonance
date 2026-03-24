package com.resonance.profile.repository;

import com.resonance.profile.entity.ProfileUser;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileUserRepository extends JpaRepository<ProfileUser, UUID> {
}