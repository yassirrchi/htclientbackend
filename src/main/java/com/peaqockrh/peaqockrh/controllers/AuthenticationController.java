package com.peaqockrh.peaqockrh.controllers;

import com.peaqockrh.peaqockrh.dto.CredentialsDto;
import com.peaqockrh.peaqockrh.dto.RefreshDTO;
import com.peaqockrh.peaqockrh.dto.UserDTO;
import com.peaqockrh.peaqockrh.entities.User;
import com.peaqockrh.peaqockrh.mappers.UserMapper;
import com.peaqockrh.peaqockrh.security.UserAuthProvider;
import com.peaqockrh.peaqockrh.security.entities.RefreshToken;
import com.peaqockrh.peaqockrh.security.services.RefreshTokenService;
import com.peaqockrh.peaqockrh.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final UserAuthProvider userAuthProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserMapper userMapper;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredentialsDto credentialsDto){
        //System.out.println(credentialsDto.email()+" thisis "+credentialsDto.password());
       UserDTO user=userService.login(credentialsDto);
       user.setToken(userAuthProvider.createToken(user));
       user.setRefreshToken(refreshTokenService.createRefreshToken(user.getEmail()));
        return ResponseEntity.ok(user);
    }
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshDTO refreshRequest){

        System.out.println(refreshRequest.refresh());
        RefreshToken refreshToken=refreshTokenService.findByToken(refreshRequest.refresh()).orElse(null);
        if(refreshToken==null){
            throw new RuntimeException("Refresh is not found");
        }

        System.out.println(refreshToken.getToken());
        User user=refreshTokenService.verifyExpiration(refreshToken).getUser();
        UserDTO userdto=userMapper.toUserDTO(user);
        userdto.setToken(userAuthProvider.createToken(userdto));
        userdto.setRefreshToken(refreshTokenService.createRefreshToken(userdto.getEmail()));
        return ResponseEntity.ok(userdto);

        ///
        /*
      refreshTokenService.findByToken(refreshRequest.refresh())
               .map(refreshTokenService::verifyExpiration)
               .map(RefreshToken::getUser)
               .map(user -> {
                   UserDTO userDTO=userMapper.toUserDTO(user);
                   userDTO.setToken(userAuthProvider.createToken(userDTO));
                   userDTO.setRefreshToken(refreshTokenService.createRefreshToken(user.getEmail()));
                   return ResponseEntity.ok(userDTO);
               }).orElseThrow(()->
                   new RuntimeException("REFRESH NOT FOUND"));*/


    }

}
