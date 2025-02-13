package com.example.schedule_jpa.controller;

import com.example.schedule_jpa.dto.UserRequestDto;
import com.example.schedule_jpa.dto.UserResponseDto;
import com.example.schedule_jpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> save(@RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(userService.save(dto));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
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
