package com.musicplatform.usersservice.mapper;

import com.musicplatform.usersservice.dto.UserDTO;
import com.musicplatform.usersservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper{
    public UserDTO toUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setCreatedAt(user.getCreatedAt());
        return userDTO;
    }
    public User toUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setCreatedAt(userDTO.getCreatedAt());
        return user;
    }
}
