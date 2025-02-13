package com.example.schedule_jpa.repository;

import com.example.schedule_jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
