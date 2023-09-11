package com.HSRMApp.controllers;


import com.HSRMApp.dto.SalaryDto;
import com.HSRMApp.entity.Salary;
import com.HSRMApp.service.SalaryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/manager/salaries/")

public class ManageSalaryController {

    private SalaryService salaryService;

    //Add product
    @PostMapping
    public ResponseEntity<SalaryDto> createdSalary(@RequestBody SalaryDto salaryDto){
        SalaryDto savedSalary = salaryService.createdSalary(salaryDto);
        return new ResponseEntity<>(savedSalary, HttpStatus.CREATED);
    }

    //Get product
    @GetMapping("{id}")
    public  ResponseEntity<SalaryDto> getProductById(@PathVariable("id") Long salaryId){
        SalaryDto salaryDto = salaryService.getSalaryById(salaryId);
        return ResponseEntity.ok(salaryDto);
    }

    //Get all
    @GetMapping
    public ResponseEntity<List<SalaryDto>> getAllProducts(){
        List<SalaryDto> salaries = salaryService.getAllSalary();
        return ResponseEntity.ok(salaries);
    }

    //Update
    @PutMapping("{id}")
    public ResponseEntity<SalaryDto> updateSalary(@PathVariable("id") Long salaryId,
                                                    @RequestBody SalaryDto updateSalary) {
        SalaryDto salaryDto = salaryService.updateSalary(salaryId, updateSalary);
        return ResponseEntity.ok(salaryDto);
    }

    //Delete
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSalary(@PathVariable("id") Long salaryId){
        salaryService.deleteSalary(salaryId);
        return ResponseEntity.ok("Deleted successfully! ");
    }
}
