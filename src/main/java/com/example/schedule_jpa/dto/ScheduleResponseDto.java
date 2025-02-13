package com.example.schedule_jpa.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private final Long id;
    private final String member;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ScheduleResponseDto(Long id,
                               String member,
                               String title,
                               String content,
                               LocalDateTime createdAt,
                               LocalDateTime updatedAt
    ) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
