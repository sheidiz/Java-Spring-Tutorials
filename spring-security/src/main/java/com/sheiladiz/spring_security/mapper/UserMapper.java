package com.sheiladiz.spring_security.mapper;

import com.sheiladiz.spring_security.dto.UserDto;
import com.sheiladiz.spring_security.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDto toDTO(User user){
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .build();
    }

    public User toEntity(UserDto user){
        return User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .build();
    }

    public List<UserDto> usersToUserDtos(List<User> userEntities) {
        return userEntities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
