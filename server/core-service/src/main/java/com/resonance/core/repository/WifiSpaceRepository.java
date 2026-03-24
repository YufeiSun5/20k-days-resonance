package com.resonance.core.repository;

import com.resonance.core.entity.WifiSpace;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WifiSpaceRepository extends JpaRepository<WifiSpace, UUID> {

    Optional<WifiSpace> findByHashedBssid(String hashedBssid);
}