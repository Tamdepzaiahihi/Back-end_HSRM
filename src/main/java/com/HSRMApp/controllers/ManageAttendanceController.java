package com.HSRMApp.controllers;

import com.HSRMApp.dto.AttendanceDto;
import com.HSRMApp.service.AttendanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/manager/attendances")
@AllArgsConstructor
public class ManageAttendanceController {

    private AttendanceService attendanceService;

    @GetMapping
    public ResponseEntity<List<AttendanceDto>> getAllAttendances() {
        List<AttendanceDto> attendances = attendanceService.getAllAttendances();
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDto> getAttendanceById(@PathVariable("id") Long attendanceId) {
        AttendanceDto attendanceDto = attendanceService.getAttendanceById(attendanceId);
        return ResponseEntity.ok(attendanceDto);
    }

    @PostMapping
    public ResponseEntity<AttendanceDto> createAttendance(@RequestBody AttendanceDto attendanceDto) {
        AttendanceDto createdAttendance = attendanceService.createAttendance(attendanceDto);
        return ResponseEntity.ok(createdAttendance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceDto> updateAttendance(
            @PathVariable("id") Long attendanceId,
            @RequestBody AttendanceDto updatedAttendance) {
        AttendanceDto attendance = attendanceService.updateAttendance(attendanceId, updatedAttendance);
        return ResponseEntity.ok(attendance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable("id") Long attendanceId) {
        attendanceService.deleteAttendance(attendanceId);
        return ResponseEntity.noContent().build();
    }
}
