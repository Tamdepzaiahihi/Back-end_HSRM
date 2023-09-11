package com.HSRMApp.controllers;

import com.HSRMApp.dto.BasicStaffGroupDto;
import com.HSRMApp.dto.CredentialsDto;
import com.HSRMApp.dto.UpdatePasswordDto;
import com.HSRMApp.service.BasicStaffGroupAccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/basic_staff/account")
@Transactional
@RequiredArgsConstructor
public class BasicStaffGroupAccountController {

    private final BasicStaffGroupAccountService staffAccountService;

    @GetMapping
    public ResponseEntity<BasicStaffGroupDto> getAccount(
            @AuthenticationPrincipal String name) {

        BasicStaffGroupDto account = staffAccountService.loadAccount(name);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/password")
    public ResponseEntity<Void> updatePassword(
            @RequestBody UpdatePasswordDto updatePasswordDto) {
        CredentialsDto oldCredentials = updatePasswordDto.getOldCredentials();
        CredentialsDto newCredentials = updatePasswordDto.getNewCredentials();

        staffAccountService.updatePassword(oldCredentials, newCredentials);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<BasicStaffGroupDto> updateAccount(
            @RequestBody BasicStaffGroupDto basicStaffGroupDto) {
        BasicStaffGroupDto account = staffAccountService.updateAccount(basicStaffGroupDto);
        return ResponseEntity.ok(account);
    }




}
