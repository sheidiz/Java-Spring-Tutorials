package com.sheiladiz.spring_security.service;

import com.sheiladiz.spring_security.dto.LoginUserDto;
import com.sheiladiz.spring_security.dto.RegisterUserDto;
import com.sheiladiz.spring_security.model.User;
import com.sheiladiz.spring_security.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public User signUp(RegisterUserDto registerUserDto){
        User user = User.builder()
                .email(registerUserDto.getEmail())
                .fullName(registerUserDto.getFullName())
                .password(passwordEncoder.encode(registerUserDto.getPassword()))
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();

        if(userRepository.findByEmail(registerUserDto.getEmail()).isPresent()){
            throw new RuntimeException("Email already registered");
        }

        try {
            User savedUser = userRepository.save(user);
            return savedUser;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public User authenticate(LoginUserDto loginUserDto){
        User user = userRepository.findByEmail(loginUserDto.getEmail()).orElseThrow(()-> new RuntimeException("User not found"));

        if(!user.isEnabled()){
            throw new RuntimeException("Account is disabled, try to contact support.");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.getEmail(),
                        loginUserDto.getPassword()
                )
        );

        return user;
    }
}
