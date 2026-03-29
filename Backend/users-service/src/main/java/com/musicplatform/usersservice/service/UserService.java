package com.musicplatform.usersservice.service;

import com.musicplatform.usersservice.dto.UserDTO;
import com.musicplatform.usersservice.entity.User;
import com.musicplatform.usersservice.mapper.UserMapper;
import com.musicplatform.usersservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private  final UserRepository userRepository;
    private  final UserMapper userMapper;

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toUserDTO)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    public UserDTO register(UserDTO userDTO) {
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        User saved = userRepository.save(userMapper.toUser(userDTO));
        return userMapper.toUserDTO(saved);
    }

}
