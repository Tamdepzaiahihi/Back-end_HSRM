package com.HSRMApp.repository;

import com.HSRMApp.entity.Token;
import com.HSRMApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    boolean existsByToken(String token);

    void deleteByUser(User user);
}
