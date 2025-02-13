package com.example.schedule_jpa.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {

    private Long id;
    private String member;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserResponseDto(Long id,
                           String member,
                           String email,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt
    ) {
        this.id = id;
        this.member = member;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
