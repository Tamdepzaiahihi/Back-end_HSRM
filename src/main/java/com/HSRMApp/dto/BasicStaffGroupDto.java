package com.HSRMApp.dto;

import com.HSRMApp.entity.Department;
import com.HSRMApp.entity.Position;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicStaffGroupDto {
    private String uuid;

    private String username;

    private String firstName;

    private String lastName;

    private Integer numberCard;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    private Character sex;

    private String placeOfOrigin;

    private String nationality;

    @JsonFormat(pattern="yyyy-MM-dd")
    private  Date dateOfExpiry;

    private  String placeOfResidence;

    private  Integer phoneNumber;

    private String email;

    private Integer bankNumber;



}
