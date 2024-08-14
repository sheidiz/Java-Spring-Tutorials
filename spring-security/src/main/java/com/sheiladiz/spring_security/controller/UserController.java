package com.sheiladiz.spring_security.controller;

import com.sheiladiz.spring_security.dto.UserDto;
import com.sheiladiz.spring_security.mapper.UserMapper;
import com.sheiladiz.spring_security.model.User;
import com.sheiladiz.spring_security.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        User currentUser = userDetails;

        return ResponseEntity.ok(userMapper.toDTO(currentUser));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> allUsers() {
        List <User> users = userService.allUsers();
        return ResponseEntity.ok(userMapper.usersToUserDtos(users));
    }
}
