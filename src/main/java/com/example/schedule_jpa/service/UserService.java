package com.example.schedule_jpa.service;

import com.example.schedule_jpa.dto.UserRequestDto;
import com.example.schedule_jpa.dto.UserResponseDto;
import com.example.schedule_jpa.entity.User;
import com.example.schedule_jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto save(UserRequestDto dto) {
        User user = new User(dto.getMember(),
                dto.getEmail(),
                dto.getCreatedAt(),
                dto.getUpdatedAt()
        );
        User savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser.getId(),
                savedUser.getMember(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getUpdatedAt()
        );

    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> findAll() {
        List<User> users = userRepository.findAll();

        List<UserResponseDto> dtos = new ArrayList<>();
        for (User user : users) {
            dtos.add(new UserResponseDto(user.getId(),
                    user.getMember(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getUpdatedAt())
            );
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("유저정보가 없습니다.")
        );
        return new UserResponseDto(user.getId(),
                user.getMember(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    @Transactional
    public UserResponseDto update(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일치하는 정보가 없습니다.")
        );

        user.update(dto.getMember(),
                dto.getEmail(),
                dto.getCreatedAt(),
                dto.getUpdatedAt()
        );
        return new UserResponseDto(user.getId(),
                user.getMember(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("일치하는 정보가 없어 삭제가 불가능합니다.");
        }

        userRepository.deleteById(id);
    }
}
