package com.HSRMApp.mapper;

import com.HSRMApp.dto.BasicStaffGroupDto;
import com.HSRMApp.mapper.BasicStaffGroupMapper;
import com.HSRMApp.entity.BasicStaffGroup;
import com.HSRMApp.entity.User;
import com.HSRMApp.exception.RestException;
import com.HSRMApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffMapperImpl implements BasicStaffGroupMapper {
    private final UserRepository userRepository;

    @Override
    public BasicStaffGroupDto toDto(BasicStaffGroup staff) {
        return BasicStaffGroupDto.builder()
                .username(staff.getUser().getUsername())
                .firstName(staff.getFirstName())
                .lastName(staff.getLastName())
                .numberCard(staff.getNumberCard())
                .birthday(staff.getBirthday())
                .sex(staff.getSex())
                .placeOfOrigin(staff.getPlaceOfOrigin())
                .nationality(staff.getNationality())
                .dateOfExpiry(staff.getDateOfExpiry())
                .placeOfResidence(staff.getPlaceOfResidence())
                .phoneNumber(staff.getPhoneNumber())
                .email(staff.getEmail())
                .bankNumber(staff.getBankNumber())

                .build();
    }

    @Override
    public BasicStaffGroup toEntity(BasicStaffGroupDto basicStaffGroupDto) {
        User user = userRepository.findByUsername(basicStaffGroupDto.getUsername())
                .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "User not found"));

        return BasicStaffGroup.builder()
                .firstName(basicStaffGroupDto.getFirstName())
                .lastName(basicStaffGroupDto.getLastName())
                .numberCard(basicStaffGroupDto.getNumberCard())
                .birthday(basicStaffGroupDto.getBirthday())
                .sex(basicStaffGroupDto.getSex())
                .placeOfOrigin(basicStaffGroupDto.getPlaceOfOrigin())
                .nationality(basicStaffGroupDto.getNationality())
                .dateOfExpiry(basicStaffGroupDto.getDateOfExpiry())
                .placeOfResidence(basicStaffGroupDto.getPlaceOfResidence())
                .phoneNumber(basicStaffGroupDto.getPhoneNumber())
                .email(basicStaffGroupDto.getEmail())
                .bankNumber(basicStaffGroupDto.getBankNumber())
                .user(user)
                .build();
    }



}
