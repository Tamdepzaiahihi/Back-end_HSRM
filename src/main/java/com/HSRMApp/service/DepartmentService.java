package com.HSRMApp.service;



import com.HSRMApp.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto createdDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentById(Long departmentId);

    List<DepartmentDto> getAllDepartment();

    DepartmentDto updateDepartment(Long departmentId, DepartmentDto updateDepartment);

    void deleteDepartment(Long departmentId);
}
