package com.HSRMApp.service;

import com.HSRMApp.dto.SalaryDto;

import java.util.List;

public interface SalaryService {
    SalaryDto createdSalary(SalaryDto salaryDto);

    SalaryDto getSalaryById(Long salaryId);

    List<SalaryDto> getAllSalary();

    SalaryDto updateSalary(Long salaryId, SalaryDto updateSalary);

    void deleteSalary(Long salaryId);
}
