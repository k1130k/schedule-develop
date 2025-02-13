package com.example.schedule_jpa.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserRequestDto {

    private String member;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
