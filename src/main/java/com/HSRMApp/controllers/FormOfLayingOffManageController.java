package com.HSRMApp.controllers;



import com.HSRMApp.dto.FormOfLayingOffDto;
import com.HSRMApp.service.FormOfLayingOffService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/manager/formOfLayingOff")
@AllArgsConstructor
public class FormOfLayingOffManageController {

    private FormOfLayingOffService formOfLayingOffService;

    @GetMapping
    public ResponseEntity<List<FormOfLayingOffDto>> getAllFormOfLayingOff() {
        List<FormOfLayingOffDto> formOfLayingOff = formOfLayingOffService.getAllFormOfLayingOff();
        return ResponseEntity.ok(formOfLayingOff);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormOfLayingOffDto> getFormOffLayingOffById(@PathVariable("id") Long formOffLayingOffId) {
        FormOfLayingOffDto formOfLayingOff = formOfLayingOffService.getFormOfLayingOffById(formOffLayingOffId);
        return ResponseEntity.ok(formOfLayingOff);
    }

    @PostMapping
    public ResponseEntity<FormOfLayingOffDto> createFormOfLayingOff(@RequestBody FormOfLayingOffDto formOfLayingOffDto) {
        FormOfLayingOffDto createdFormOfLayingOff = formOfLayingOffService.createFormOfLayingOff(formOfLayingOffDto);
        return ResponseEntity.ok(createdFormOfLayingOff);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormOfLayingOffDto> updateFormOffLayingOff(
            @PathVariable("id") Long formOffLayingOffId,
            @RequestBody FormOfLayingOffDto updatedFormOffLayingOff) {
        FormOfLayingOffDto formOfLayingOffDto = formOfLayingOffService.updateFormOfLayingOff(formOffLayingOffId, updatedFormOffLayingOff);
        return ResponseEntity.ok(formOfLayingOffDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormOffLayingOff(@PathVariable("id") Long formOffLayingOffId) {
        formOfLayingOffService.deleteFormOfLayingOff(formOffLayingOffId);
        return ResponseEntity.noContent().build();
    }
}
