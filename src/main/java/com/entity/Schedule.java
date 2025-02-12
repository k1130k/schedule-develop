package com.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Schedule extends  BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String member;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user id", nullable = false)
    private  User user;

    public Schedule(String member,
                    String title,
                    String content,
                    LocalDateTime createdAt,
                    LocalDateTime updatedAt,
                    User user
    ) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }

    public void update(String member,
                       String title,
                       String content,
                       LocalDateTime createdAt,
                       LocalDateTime updatedAt
    ) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
