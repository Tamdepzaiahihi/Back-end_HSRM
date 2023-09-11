package com.HSRMApp.service;

import com.HSRMApp.dto.BasicStaffGroupDto;


import com.HSRMApp.dto.CredentialsDto;
import com.HSRMApp.entity.BasicStaffGroup;

import com.HSRMApp.entity.User;
import com.HSRMApp.exception.RestException;

import com.HSRMApp.mapper.BasicStaffGroupMapper;
import com.HSRMApp.repository.BasicStaffGroupRepository;

import com.HSRMApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasicStaffGroupAccountService {
    private final PasswordEncoder passwordEncoder;
    private final BasicStaffGroupRepository staffRepository;

    private final UserRepository userRepository;

    private final BasicStaffGroupMapper staffMapper;


    public BasicStaffGroupDto loadAccount(String username) {
        BasicStaffGroup staff = findStaffByUsername(username);
        return staffMapper.toDto(staff);
    }

    public BasicStaffGroup findStaffByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "User not found"));

        return staffRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "Staff not found"));
    }

    public BasicStaffGroupDto updateAccount(BasicStaffGroupDto basicStaffGroupDto) {
        BasicStaffGroup staff = staffMapper.toEntity(basicStaffGroupDto);
        BasicStaffGroup savedStaff = staffRepository.save(staff);
        return staffMapper.toDto(savedStaff);
    }

    public void updatePassword(CredentialsDto oldCredentials, CredentialsDto newCredentials) {
        User user = userRepository.findByUsername(newCredentials.getUsername())
                .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "User not found"));

        if (!passwordEncoder.matches(oldCredentials.getPassword(), user.getPassword())) {
            throw new RestException(HttpStatus.BAD_REQUEST, "Invalid credentials");
        }

        user.setPassword(passwordEncoder.encode(newCredentials.getPassword()));
        userRepository.save(user);
    }


}
