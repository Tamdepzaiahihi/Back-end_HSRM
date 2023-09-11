package com.HSRMApp.service.impl;

import com.HSRMApp.dto.DepartmentDto;
import com.HSRMApp.entity.Department;
import com.HSRMApp.exception.ResourceNotFoundException;
import com.HSRMApp.mapper.DepartmentMapper;
import com.HSRMApp.repository.DepartmentRepository;
import com.HSRMApp.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class DepartmentServicesImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createdDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department is not exist with given id: " + departmentId));
        return DepartmentMapper.mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartment() {
        List<Department> departments= departmentRepository.findAll();
        return departments.stream()
                .map(DepartmentMapper::mapToDepartmentDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updateDepartment) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Departments is not exist with given id: " + departmentId));

        department.setId(updateDepartment.getId());
        department.setName(updateDepartment.getName());
        department.setLocal(updateDepartment.getLocal());
        department.setUser(updateDepartment.getUser());




        Department updateDepartmentsObj = departmentRepository.save(department);

        return DepartmentMapper.mapToDepartmentDto(updateDepartmentsObj);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department is not exist with given id: " + departmentId));

        departmentRepository.deleteById(departmentId);
    }
}
