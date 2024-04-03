package com.peaqockrh.peaqockrh.security.services;

import com.peaqockrh.peaqockrh.repositories.UserRepository;
import com.peaqockrh.peaqockrh.security.entities.RefreshToken;
import com.peaqockrh.peaqockrh.security.repositories.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    @Override
    public RefreshToken createRefreshToken(String email) {
        RefreshToken refreshToken=RefreshToken.builder()
                .user(userRepository.findByEmail(email).get())
                .token(UUID.randomUUID().toString())
                .expiration(Instant.now().plusMillis(60000)).build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if(refreshToken.getExpiration().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("refresh token expired");
        }
        return refreshToken;
    }

}
