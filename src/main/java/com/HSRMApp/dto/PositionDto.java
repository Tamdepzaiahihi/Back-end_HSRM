package com.HSRMApp.dto;


import com.HSRMApp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PositionDto {
    private Long id;

    private String name;

    private User user;
}
