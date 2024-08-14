package com.sheiladiz.spring_security.service;

import com.sheiladiz.spring_security.dto.UserDto;
import com.sheiladiz.spring_security.model.User;
import com.sheiladiz.spring_security.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.isPresent() ? foundUser.get() : null;
    }
}
