package com.HSRMApp.mapper;

import com.HSRMApp.dto.FormOfLayingOffDto;
import com.HSRMApp.entity.FormOfLayingOff;

public class FormOfLayingOffMapper {
    public static FormOfLayingOffDto mapToFormOfLayingOffDto(FormOfLayingOff formOfLayingOff){
        return new FormOfLayingOffDto(
                formOfLayingOff.getId(),
                formOfLayingOff.getDate(),
                formOfLayingOff.getReason(),
                formOfLayingOff.getSeverancePacket(),
                formOfLayingOff.getUser()
        );

    }

    public static FormOfLayingOff mapToFormOfLayingOff(FormOfLayingOffDto formOfLayingOffDto){
        return new FormOfLayingOff(
                formOfLayingOffDto.getId(),
                formOfLayingOffDto.getDate(),
                formOfLayingOffDto.getReason(),
                formOfLayingOffDto.getSeverancePacket(),
                formOfLayingOffDto.getUser()
        );
    }
}
