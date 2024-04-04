package com.peaqockrh.peaqockrh.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.peaqockrh.peaqockrh.dto.UserDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;
@Component
@RequiredArgsConstructor

public class UserAuthProvider {
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;
    @PostConstruct
    protected void init(){
        secretKey= Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
    public String createToken(UserDTO userDTO){
        Date now=new Date();
        Date validity=new Date(now.getTime()+30000);
        return JWT.create().withIssuer(userDTO.getEmail())
                .withIssuedAt(now).withExpiresAt(validity)
                .withClaim("email",userDTO.getEmail())
                .withClaim("role",userDTO.getRole())
                .sign(Algorithm.HMAC256(secretKey));
    }
    public Authentication validateToken(String token){
        Algorithm algorithm=Algorithm.HMAC256(secretKey);
        JWTVerifier verifier=JWT.require(algorithm).build();
        DecodedJWT decodedJWT=verifier.verify(token);
        UserDTO user= UserDTO.builder().email(decodedJWT.getIssuer()).build();
        return new UsernamePasswordAuthenticationToken(user,null, Collections.emptyList());
    }
}
