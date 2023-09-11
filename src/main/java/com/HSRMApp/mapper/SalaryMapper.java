package com.HSRMApp.mapper;

import com.HSRMApp.dto.SalaryDto;
import com.HSRMApp.entity.Salary;

public class SalaryMapper {
    public static SalaryDto mapToSalaryDto(Salary salary){
        return new SalaryDto(
                salary.getId(),
                salary.getAmount(),
                salary.getCoefficient(),
                salary.getBonus(),
                salary.getTotal(),
                salary.getEffectiveDate(),
                salary.getUser()
        );
    }

    public static Salary mapToSalary(SalaryDto salaryDto){
        return new Salary(
                salaryDto.getId(),
                salaryDto.getAmount(),
                salaryDto.getCoefficient(),
                salaryDto.getBonus(),
                salaryDto.getTotal(),
                salaryDto.getEffectiveDate(),
                salaryDto.getUser()
        );
    }


}
