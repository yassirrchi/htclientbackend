package com.peaqockrh.peaqockrh.dto;

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
}
