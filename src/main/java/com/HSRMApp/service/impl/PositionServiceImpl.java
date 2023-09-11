package com.HSRMApp.service.impl;

import com.HSRMApp.dto.PositionDto;


import com.HSRMApp.entity.Position;
import com.HSRMApp.exception.ResourceNotFoundException;
import com.HSRMApp.mapper.PositionMapper;
import com.HSRMApp.repository.PositionRepository;
import com.HSRMApp.service.PositionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PositionServiceImpl implements PositionService {

    private PositionRepository positionRepository;

    @Override
    public PositionDto createdPosition(PositionDto positionDto) {
        Position position = PositionMapper.mapToPosition(positionDto);
        Position savedPosition = positionRepository.save(position);
        return PositionMapper.mapToPositionDto(savedPosition);
    }

    @Override
    public PositionDto getPositionById(Long positionId) {
        Position position = positionRepository.findById(positionId)
                .orElseThrow(() -> new ResourceNotFoundException("Position is not exist with given id: " + positionId));
        return PositionMapper.mapToPositionDto(position);
    }

    @Override
    public List<PositionDto> getAllPosition() {
        List<Position> positions= positionRepository.findAll();
        return positions.stream()
                .map(PositionMapper::mapToPositionDto)
                .collect(Collectors.toList());
    }

    @Override
    public PositionDto updatePosition(Long positionId, PositionDto updatePosition) {
        Position position = positionRepository.findById(positionId)
                .orElseThrow(() -> new ResourceNotFoundException("Position is not exist with given id: " + positionId));

        position.setId(updatePosition.getId());
        position.setName(updatePosition.getName());
        position.setUser(updatePosition.getUser());



        Position updatePositionObj = positionRepository.save(position);

        return PositionMapper.mapToPositionDto(updatePositionObj);
    }

    @Override
    public void deletePosition(Long positionId) {
        Position position = positionRepository.findById(positionId)
                .orElseThrow(() -> new ResourceNotFoundException("Position is not exist with given id: " + positionId));

       positionRepository.deleteById(positionId);
    }
}
