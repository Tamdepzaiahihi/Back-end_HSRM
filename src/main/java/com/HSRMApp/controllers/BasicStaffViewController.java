package com.HSRMApp.controllers;


import com.HSRMApp.dto.AttendanceDto;
import com.HSRMApp.dto.DepartmentDto;
import com.HSRMApp.dto.PositionDto;
import com.HSRMApp.dto.SalaryDto;
import com.HSRMApp.service.AttendanceService;
import com.HSRMApp.service.DepartmentService;
import com.HSRMApp.service.PositionService;
import com.HSRMApp.service.SalaryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/basic_staff/view")
public class BasicStaffViewController {
    private SalaryService salaryService;
    private AttendanceService attendanceService;

    private PositionService positionService;

    private DepartmentService departmentService;

    @GetMapping("/salary/{id}")
    public ResponseEntity<SalaryDto> getProductById(@PathVariable("id") Long salaryId){
        SalaryDto salaryDto = salaryService.getSalaryById(salaryId);
        return ResponseEntity.ok(salaryDto);
    }

     @GetMapping("/attendance/{id}")
     public ResponseEntity<AttendanceDto> getAttendanceById(@PathVariable("id") Long attendanceId) {
         AttendanceDto attendanceDto = attendanceService.getAttendanceById(attendanceId);
         return ResponseEntity.ok(attendanceDto);
    }

    @GetMapping("/position/{id}")
    public  ResponseEntity<PositionDto> getPositionById(@PathVariable("id") Long positionId){
        PositionDto positionDto = positionService.getPositionById(positionId);
        return ResponseEntity.ok(positionDto);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long departmentId){
        DepartmentDto departmentDto = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(departmentDto);
    }
}
