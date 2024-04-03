package com.peaqockrh.peaqockrh;

import com.peaqockrh.peaqockrh.entities.User;
import com.peaqockrh.peaqockrh.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PeaqockRhApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeaqockRhApplication.class, args);
	}
	@Bean
	CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder){
		return args -> {
			User user=new User();
			user.setEmail("admin@gmail.com");
			user.setPassword(passwordEncoder.encode("123"));
			userRepository.save(user);
		};
	}

}
