package com.peaqockrh.peaqockrh.security.repositories;

import com.peaqockrh.peaqockrh.security.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    public Optional<RefreshToken> findByToken(String token);
}
