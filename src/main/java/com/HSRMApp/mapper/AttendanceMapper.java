package com.HSRMApp.mapper;

import com.HSRMApp.dto.AttendanceDto;
import com.HSRMApp.entity.Attendance;

public class AttendanceMapper {


    public static AttendanceDto mapToAttendanceDto(Attendance attendance) {
        return new AttendanceDto(
                attendance.getId(),
                attendance.getDate(),
                attendance.getStatus(),
                attendance.getUser()

        );


    }

    public static Attendance mapToAttendance(AttendanceDto attendanceDto) {
        return new Attendance(
                attendanceDto.getId(),
                attendanceDto.getDate(),
                attendanceDto.getStatus(),
                attendanceDto.getUser()

        );
    }
}
