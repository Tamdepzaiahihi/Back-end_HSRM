package com.HSRMApp.mapper;


import com.HSRMApp.dto.BasicStaffGroupDto;
import com.HSRMApp.entity.BasicStaffGroup;

public interface BasicStaffGroupMapper {
    BasicStaffGroupDto toDto(BasicStaffGroup staff);

    BasicStaffGroup toEntity(BasicStaffGroupDto basicStaffGroupDto);
}
