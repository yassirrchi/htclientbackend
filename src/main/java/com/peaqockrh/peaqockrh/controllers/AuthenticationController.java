package com.peaqockrh.peaqockrh.controllers;

import com.peaqockrh.peaqockrh.dto.CredentialsDto;
import com.peaqockrh.peaqockrh.dto.UserDTO;
import com.peaqockrh.peaqockrh.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredentialsDto credentialsDto){
       UserDTO user=userService.login(credentialsDto);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/login")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("passed the test");
    }
}
