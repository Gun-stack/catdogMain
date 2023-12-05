package com.kosta.catdog.repository;

import com.kosta.catdog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(String id);
    Optional<User> findByProviderAndProviderId(String provider, String providerId);
}
