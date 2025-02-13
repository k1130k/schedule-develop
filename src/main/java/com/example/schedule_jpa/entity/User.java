package com.example.schedule_jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String member;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private  String password;

    public User(String member,
                String email,
                LocalDateTime createdAt,
                LocalDateTime updatedAt
    ) {
        this.member = member;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void update(String member,
                       String email,
                       LocalDateTime createdAt,
                       LocalDateTime updatedAt
    ) {
        this.member = member;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;}
}
