package com.musicplatform.usersservice.controller;

import com.musicplatform.usersservice.dto.UserDTO;
import com.musicplatform.usersservice.entity.User;
import com.musicplatform.usersservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController{
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register( @RequestBody UserDTO userDTO){
        return ResponseEntity.status(201).body(userService.register(userDTO));
    }
}
