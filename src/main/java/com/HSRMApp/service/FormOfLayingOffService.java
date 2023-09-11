package com.HSRMApp.service;


import com.HSRMApp.dto.FormOfLayingOffDto;

import java.util.List;

public interface FormOfLayingOffService {
    List<FormOfLayingOffDto> getAllFormOfLayingOff();
    FormOfLayingOffDto getFormOfLayingOffById(Long formOfLayingOffId);
    FormOfLayingOffDto createFormOfLayingOff(FormOfLayingOffDto formOfLayingOffDto);
    FormOfLayingOffDto updateFormOfLayingOff(Long formOfLayingOffId, FormOfLayingOffDto updateFormOfLayingOff);
    void deleteFormOfLayingOff(Long formOfLayingOffId);
}
