package com.HSRMApp.controllers;


import com.HSRMApp.dto.CredentialsDto;
import com.HSRMApp.dto.ManagerGroupDto;
import com.HSRMApp.dto.UpdatePasswordDto;
import com.HSRMApp.service.ManagerGroupAccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/manager/account")
@Transactional
@RequiredArgsConstructor
public class ManagerGroupAccountController {

    private final ManagerGroupAccountService managerAccountService;

    @GetMapping
    public ResponseEntity<ManagerGroupDto> getAccount(
            @AuthenticationPrincipal String name) {
        ManagerGroupDto managerGroupDto = managerAccountService.getAccount(name);
        return ResponseEntity.ok().body(managerGroupDto);
    }

    @PutMapping
    public ResponseEntity<Void> updateAccount(
            @RequestBody ManagerGroupDto managerGroupDto) {
        managerAccountService.updateAccount(managerGroupDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/password")
    public ResponseEntity<Void> updatePassword(
            @RequestBody UpdatePasswordDto updatePasswordDto) {
        CredentialsDto oldCredentials = updatePasswordDto.getOldCredentials();
        CredentialsDto newCredentials = updatePasswordDto.getNewCredentials();

        managerAccountService.updatePassword(oldCredentials, newCredentials);
        return ResponseEntity.ok().build();
    }

}
