package com.service;


import com.dto.ScheduleRequestDto;
import com.dto.ScheduleResponseDto;
import com.entity.Schedule;
import com.entity.User;
import com.repository.ScheduleRepository;
import com.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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
                dto.getContent(),
                dto.getCreatedAt(),
                dto.getUpdatedAt(),
                user
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(),
                savedSchedule.getMember(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
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
                    schedule.getContent(),
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
                schedule.getContent(),
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
                dto.getContent(),
                dto.getCreatedAt(),
                dto.getUpdatedAt()
        );
        return new ScheduleResponseDto(schedule.getId(),
                schedule.getMember(),
                schedule.getTitle(),
                schedule.getContent(),
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
