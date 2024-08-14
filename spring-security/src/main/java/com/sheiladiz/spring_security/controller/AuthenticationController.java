package com.sheiladiz.spring_security.controller;

import com.sheiladiz.spring_security.dto.LoginResponse;
import com.sheiladiz.spring_security.dto.LoginUserDto;
import com.sheiladiz.spring_security.dto.RegisterUserDto;
import com.sheiladiz.spring_security.mapper.UserMapper;
import com.sheiladiz.spring_security.model.User;
import com.sheiladiz.spring_security.service.AuthenticationService;
import com.sheiladiz.spring_security.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, UserMapper userMapper) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody RegisterUserDto registerUserDto) {
        System.out.println(registerUserDto.getEmail() + registerUserDto.getPassword());
        try{
            User registeredUser = authenticationService.signUp(registerUserDto);
            return ResponseEntity.ok(userMapper.toDTO(registeredUser));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginUserDto loginUserDto){
        try{
            User authenticatedUser = authenticationService.authenticate(loginUserDto);
            String jwtToken = jwtService.generateToken(authenticatedUser);
            LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
            return ResponseEntity.ok(loginResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
