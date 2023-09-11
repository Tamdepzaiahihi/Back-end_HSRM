package com.HSRMApp.mapper;

import com.HSRMApp.dto.PositionDto;
import com.HSRMApp.entity.Position;

public class PositionMapper {
    public static PositionDto mapToPositionDto(Position position){
        return new PositionDto(
                position.getId(),
                position.getName(),
                position.getUser()
        );
    }

    public static Position mapToPosition(PositionDto positionDto){
        return new Position(
                positionDto.getId(),
                positionDto.getName(),
                positionDto.getUser()
        );
    }
}
