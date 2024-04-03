package com.peaqockrh.peaqockrh.security.services;

import com.peaqockrh.peaqockrh.security.entities.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    public RefreshToken createRefreshToken(String email);
    public Optional<RefreshToken> findByToken(String token);
    public RefreshToken verifyExpiration(RefreshToken refreshToken);
}
