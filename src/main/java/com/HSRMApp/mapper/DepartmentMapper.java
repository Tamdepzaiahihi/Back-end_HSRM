package com.HSRMApp.mapper;

import com.HSRMApp.dto.DepartmentDto;
import com.HSRMApp.entity.Department;

public class DepartmentMapper {

    public static DepartmentDto mapToDepartmentDto(Department department){
        return new DepartmentDto(
                department.getId(),
                department.getName(),
                department.getLocal(),
                department.getUser()
        );
    }

    public static Department mapToDepartment(DepartmentDto departmentDto){
        return new Department(
                departmentDto.getId(),
                departmentDto.getName(),
                departmentDto.getLocal(),
                departmentDto.getUser()
        );
    }
}
