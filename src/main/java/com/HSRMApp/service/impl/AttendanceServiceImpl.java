package com.HSRMApp.service.impl;

import com.HSRMApp.dto.AttendanceDto;
import com.HSRMApp.entity.Attendance;
import com.HSRMApp.exception.ResourceNotFoundException;
import com.HSRMApp.mapper.AttendanceMapper;
import com.HSRMApp.repository.AttendanceRepository;
import com.HSRMApp.service.AttendanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private AttendanceRepository attendanceRepository;


    @Override
    public List<AttendanceDto> getAllAttendances() {
        List<Attendance> salaries= attendanceRepository.findAll();
        return salaries.stream()
                .map(AttendanceMapper::mapToAttendanceDto)
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceDto getAttendanceById(Long attendanceId) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance is not exist with given id: " + attendanceId));
        return AttendanceMapper.mapToAttendanceDto(attendance);
    }

    @Override
    public AttendanceDto createAttendance(AttendanceDto attendanceDto) {
       Attendance attendance = AttendanceMapper.mapToAttendance(attendanceDto);
        Attendance savedAttendance = attendanceRepository.save(attendance);
        return AttendanceMapper.mapToAttendanceDto(savedAttendance);
    }

    @Override
    public AttendanceDto updateAttendance(Long attendanceId, AttendanceDto updateAttendance) {
       Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance is not exist with given id: " + attendanceId));

        attendance.setStatus(updateAttendance.getStatus());
        attendance.setDate(updateAttendance.getDate());
        attendance.setId(updateAttendance.getId());
        attendance.setUser(updateAttendance.getUser());



        Attendance updateAttendanceObj = attendanceRepository.save(attendance);

        return AttendanceMapper.mapToAttendanceDto(updateAttendanceObj);
    }

    @Override
    public void deleteAttendance(Long attendanceId) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance is not exist with given id: " + attendanceId));

        attendanceRepository.deleteById(attendanceId);
    }
}
