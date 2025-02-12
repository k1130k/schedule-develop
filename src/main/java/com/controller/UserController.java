package com.controller;

import com.dto.UserRequestDto;
import com.dto.UserResponseDto;
import com.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public UserResponseDto save(@RequestBody UserRequestDto dto) {
        return userService.save(dto);
    }

    @GetMapping("/users")
    public List<UserResponseDto> findAll() {
        return  userService.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> findOne(@PathVariable Long id) {
        return  ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/users/{id}")
    public  ResponseEntity<UserResponseDto> update(
            @PathVariable Long id,
            @RequestBody UserRequestDto dto
    ) {
        return  ResponseEntity.ok(userService.update(id, dto));
    }

    @DeleteMapping("/users/{id}")
    public  void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
