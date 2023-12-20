package com.kosta.catdog.repository;

import com.kosta.catdog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(String id);
    Optional<User> findByProviderAndProviderId(String provider, String providerId);
        
    // 임시 비밀번호 전송 위해 이메일 주소를 사용하여 사용자 검색. 반환 타입은 Optional<User>.
    // 해당 이메일로 사용자를 찾을 경우 사용자가 존재하면 해당 사용자를 감싼 Optional 반환.
    // 존재하지 않는 경우 Optional.empty() 반환.
    Optional<User> findByEmail(String email); 
}
