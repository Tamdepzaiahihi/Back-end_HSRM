package com.HSRMApp.repository;

import com.HSRMApp.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository <Salary, Long> {
}
