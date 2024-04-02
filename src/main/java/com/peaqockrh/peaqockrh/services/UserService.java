package com.peaqockrh.peaqockrh.services;
import com.peaqockrh.peaqockrh.dto.CredentialsDto;
import com.peaqockrh.peaqockrh.dto.UserDTO;
import com.peaqockrh.peaqockrh.entities.User;
import com.peaqockrh.peaqockrh.mappers.UserMapper;
import com.peaqockrh.peaqockrh.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.nio.CharBuffer;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    public UserDTO login(CredentialsDto credentialsDto){
        User user=userRepository.findByEmail(credentialsDto.email())
                .orElseThrow(()->new RuntimeException("user not found"));
        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()),user.getPassword())){
            return userMapper.toUserDTO(user);
        }
        throw new RuntimeException("invalid credentials");
    }


}
