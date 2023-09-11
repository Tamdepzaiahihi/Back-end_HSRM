package com.HSRMApp.service.impl;

import com.HSRMApp.dto.FormOfLayingOffDto;
import com.HSRMApp.entity.FormOfLayingOff;
import com.HSRMApp.exception.ResourceNotFoundException;
import com.HSRMApp.mapper.FormOfLayingOffMapper;
import com.HSRMApp.repository.FormOfLayingOffRepository;
import com.HSRMApp.service.FormOfLayingOffService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class FormOfLayingOffServiceImpl implements FormOfLayingOffService {

    private FormOfLayingOffRepository formOfLayingOffRepository;


    @Override
    public List<FormOfLayingOffDto> getAllFormOfLayingOff() {
        List<FormOfLayingOff> formOfLayingOffs= formOfLayingOffRepository.findAll();
        return formOfLayingOffs.stream()
                .map(FormOfLayingOffMapper::mapToFormOfLayingOffDto)
                .collect(Collectors.toList());
    }

    @Override
    public FormOfLayingOffDto getFormOfLayingOffById(Long formOfLayingOffId) {
        FormOfLayingOff formOfLayingOff = formOfLayingOffRepository.findById(formOfLayingOffId)
                .orElseThrow(() -> new ResourceNotFoundException("Form of Laying Off is not exist with given id: " + formOfLayingOffId));
        return FormOfLayingOffMapper.mapToFormOfLayingOffDto(formOfLayingOff);
    }

    @Override
    public FormOfLayingOffDto createFormOfLayingOff(FormOfLayingOffDto formOfLayingOffDto) {
        FormOfLayingOff formOfLayingOff = FormOfLayingOffMapper.mapToFormOfLayingOff(formOfLayingOffDto);
        FormOfLayingOff savedFormOfLayingOff = formOfLayingOffRepository.save(formOfLayingOff);
        return FormOfLayingOffMapper.mapToFormOfLayingOffDto(savedFormOfLayingOff);
    }

    @Override
    public FormOfLayingOffDto updateFormOfLayingOff(Long formOfLayingOffId, FormOfLayingOffDto updateFormOfLayingOff) {
       FormOfLayingOff formOfLayingOff = formOfLayingOffRepository.findById(formOfLayingOffId)
                .orElseThrow(() -> new ResourceNotFoundException("Form of Laying Off is not exist with given id: " + formOfLayingOffId));

        formOfLayingOff.setId(updateFormOfLayingOff.getId());
        formOfLayingOff.setDate(updateFormOfLayingOff.getDate());
        formOfLayingOff.setReason(updateFormOfLayingOff.getReason());
        formOfLayingOff.setSeverancePacket(updateFormOfLayingOff.getSeverancePacket());
        formOfLayingOff.setUser(updateFormOfLayingOff.getUser());

        FormOfLayingOff updateFormOfLayingOffObj = formOfLayingOffRepository.save(formOfLayingOff);

        return FormOfLayingOffMapper.mapToFormOfLayingOffDto(updateFormOfLayingOffObj);
    }

    @Override
    public void deleteFormOfLayingOff(Long formOfLayingOffId) {
        FormOfLayingOff formOfLayingOff = formOfLayingOffRepository .findById(formOfLayingOffId)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance is not exist with given id: " + formOfLayingOffId));

        formOfLayingOffRepository.deleteById(formOfLayingOffId);
    }
}
