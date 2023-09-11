package com.HSRMApp.controllers;


import com.HSRMApp.dto.PositionDto;
import com.HSRMApp.dto.SalaryDto;
import com.HSRMApp.service.PositionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/manager/positions/")
public class ManagePositionController {

    private PositionService positionService;

    @PostMapping
    public ResponseEntity<PositionDto> createdPosition(@RequestBody PositionDto positionDto){
        PositionDto savedPosition = positionService.createdPosition(positionDto);
        return new ResponseEntity<>(savedPosition, HttpStatus.CREATED);
    }

    //Get
    @GetMapping("{id}")
    public  ResponseEntity<PositionDto> getPositionById(@PathVariable("id") Long positionId){
        PositionDto positionDto = positionService.getPositionById(positionId);
        return ResponseEntity.ok(positionDto);
    }

    //Get all
    @GetMapping
    public ResponseEntity<List<PositionDto>> getAllPosition(){
        List<PositionDto> positions = positionService.getAllPosition();
        return ResponseEntity.ok(positions);
    }

    //Update
    @PutMapping("{id}")
    public ResponseEntity<PositionDto> updateSalary(@PathVariable("id") Long positionId,
                                                  @RequestBody PositionDto updatePosition) {
        PositionDto positionDto = positionService.updatePosition(positionId, updatePosition);
        return ResponseEntity.ok(positionDto);
    }

    //Delete
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSalary(@PathVariable("id") Long positionId){
        positionService.deletePosition(positionId);
        return ResponseEntity.ok("Deleted successfully! ");
    }
}
