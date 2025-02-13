package com.example.schedule_jpa.service;


import com.example.schedule_jpa.dto.ScheduleRequestDto;
import com.example.schedule_jpa.dto.ScheduleResponseDto;
import com.example.schedule_jpa.entity.Schedule;
import com.example.schedule_jpa.entity.User;
import com.example.schedule_jpa.repository.ScheduleRepository;
import com.example.schedule_jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ScheduleResponseDto save(ScheduleRequestDto dto) {

        User user = userRepository.findById(dto.getUserId()).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 user입니다.")
        );
        Schedule schedule = new Schedule(dto.getMember(),
                dto.getTitle(),
                dto.getContents(),
                dto.getCreatedAt(),
                dto.getUpdatedAt(),
                user
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(),
                savedSchedule.getMember(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAll() {

        List<Schedule> schedules = scheduleRepository.findAll();

        List<ScheduleResponseDto> dtos = new ArrayList<>();

        for (Schedule schedule : schedules) {
            ScheduleResponseDto dto = new ScheduleResponseDto(schedule.getId(),
                    schedule.getMember(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getCreatedAt(),
                    schedule.getUpdatedAt()
            );
            dtos.add(dto);
        }

        return dtos;

        }

    @Transactional(readOnly = true)
    public ScheduleResponseDto findById(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일정이 없습니다.")
        );
        return new ScheduleResponseDto(schedule.getId(),
                schedule.getMember(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    @Transactional
    public  ScheduleResponseDto update(Long id, ScheduleRequestDto dto) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일차하는 정보가 없습니다.")
        );

        schedule.update(dto.getMember(),
                dto.getTitle(),
                dto.getContents(),
                dto.getCreatedAt(),
                dto.getUpdatedAt()
        );
        return new ScheduleResponseDto(schedule.getId(),
                schedule.getMember(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );

    }

    @Transactional
    public void deleteById(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new IllegalArgumentException("일치하는 정보가 없어 삭제가 불가능합니다.");
        }

        scheduleRepository.deleteById(id);
    }
}
