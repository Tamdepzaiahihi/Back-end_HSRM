package com.HSRMApp.dto;



import com.HSRMApp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDto {
        private Long id;

        private Double amount;

        private Double coefficient;

        private Double bonus;

        private Double total;


        private Date effectiveDate;

        private User user;

    }
