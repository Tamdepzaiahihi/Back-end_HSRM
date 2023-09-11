package com.HSRMApp.service.impl;

import com.HSRMApp.dto.SalaryDto;
import com.HSRMApp.entity.Salary;
import com.HSRMApp.exception.ResourceNotFoundException;
import com.HSRMApp.mapper.SalaryMapper;
import com.HSRMApp.repository.SalaryRepository;
import com.HSRMApp.service.SalaryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class SalaryServiceImpl implements SalaryService {

    private SalaryRepository salaryRepository;

    @Override
    public SalaryDto createdSalary(SalaryDto salaryDto) {
        Salary salary = SalaryMapper.mapToSalary(salaryDto);
        Salary savedSalary = salaryRepository.save(salary);
        return SalaryMapper.mapToSalaryDto(savedSalary);
    }

    @Override
    public SalaryDto getSalaryById(Long salaryId) {
        Salary salary = salaryRepository.findById(salaryId)
                .orElseThrow(() -> new ResourceNotFoundException("Salary is not exist with given id: " + salaryId));
        return SalaryMapper.mapToSalaryDto(salary);
    }

    @Override
    public List<SalaryDto> getAllSalary() {
        List<Salary> salaries= salaryRepository.findAll();
        return salaries.stream()
                .map(SalaryMapper::mapToSalaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public SalaryDto updateSalary(Long salaryId, SalaryDto updateSalary) {
        Salary salary = salaryRepository.findById(salaryId)
                .orElseThrow(() -> new ResourceNotFoundException("Product is not exist with given id: " + salaryId));

        salary.setAmount(updateSalary.getAmount());
        salary.setBonus(updateSalary.getBonus());
        salary.setCoefficient(updateSalary.getCoefficient());
        salary.setEffectiveDate(updateSalary.getEffectiveDate());
        salary.setBonus(updateSalary.getBonus());
        salary.setUser(updateSalary.getUser());


        Salary updateSalaryObj = salaryRepository.save(salary);

        return SalaryMapper.mapToSalaryDto(updateSalaryObj);
    }

    @Override
    public void deleteSalary(Long salaryId) {
        Salary salary = salaryRepository.findById(salaryId)
                .orElseThrow(() -> new ResourceNotFoundException("Product is not exist with given id: " + salaryId));

        salaryRepository.deleteById(salaryId);
    }
}
