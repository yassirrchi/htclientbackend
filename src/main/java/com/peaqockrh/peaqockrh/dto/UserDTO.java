package com.peaqockrh.peaqockrh.dto;

import com.peaqockrh.peaqockrh.security.entities.RefreshToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String token;
    private String role;
    private RefreshToken refreshToken;
}
