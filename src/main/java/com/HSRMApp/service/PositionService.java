package com.HSRMApp.service;

import com.HSRMApp.dto.PositionDto;

import java.util.List;

public interface PositionService {
    PositionDto createdPosition(PositionDto positionDto);

    PositionDto getPositionById(Long positionId);

    List<PositionDto> getAllPosition();

    PositionDto updatePosition(Long positionId, PositionDto updatePosition);

    void deletePosition(Long positionId);


}
