package com.HSRMApp.service;

import com.HSRMApp.dto.AttendanceDto;

import java.util.List;

public interface AttendanceService {
    List<AttendanceDto> getAllAttendances();
    AttendanceDto getAttendanceById(Long attendanceId);
    AttendanceDto createAttendance(AttendanceDto attendanceDto);
    AttendanceDto updateAttendance(Long attendanceId, AttendanceDto updateAttendance);
    void deleteAttendance(Long attendanceId);

}
