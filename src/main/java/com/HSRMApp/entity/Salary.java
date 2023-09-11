package com.HSRMApp.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "salary")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private Double coefficient;

    private Double bonus;

    private Double total;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date effectiveDate;

    @ManyToOne
    @JoinColumn(name = "users_uuid")
    @JsonIgnore
    private User user;

    public void setAmount(Double amount) {
        this.amount = amount;
        recalculateTotal();
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
        recalculateTotal();
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
        recalculateTotal();
    }


    private void recalculateTotal() {
        if (amount != null && coefficient != null && bonus != null) {
            total = amount * coefficient + bonus;
        } else if (amount != null && coefficient != null) {
            total = amount * coefficient;
        }
        else {
            total = null;
        }
    }
}