package com.HSRMApp.dto;

import com.HSRMApp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormOfLayingOffDto {
    private Long id;

    private Date date;

    private String reason;

    private String severancePacket;

    private User user;
}
