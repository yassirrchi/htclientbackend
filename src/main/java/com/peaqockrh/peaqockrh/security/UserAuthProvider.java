package com.peaqockrh.peaqockrh.security;

import com.peaqockrh.peaqockrh.dto.UserDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

import java.util.Base64;

public class UserAuthProvider {
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;
    @PostConstruct
    protected void init(){
        secretKey= Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
    public String createToken(UserDTO userDTO){
        return null;

    }
}
