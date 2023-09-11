package com.HSRMApp.controllers;

import com.HSRMApp.config.JwtManagerGroupAuthenticationFilter;
import com.HSRMApp.dto.CredentialsDto;
import com.HSRMApp.dto.ManagerGroupDto;
import com.HSRMApp.service.JwtService;
import com.HSRMApp.service.ManagerGroupAuthService;
import com.HSRMApp.service.TokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/manager/auth")
@Transactional
@RequiredArgsConstructor
public class ManagerGroupAuthenticationController {

    private final ManagerGroupAuthService managerAuthService;
    private final JwtService jwtService;
    private final TokenService tokenService;

    @GetMapping
    public String hello() {
        return "Hello, Manager!";
    }

    @PostMapping("/login")
    public ResponseEntity<ManagerGroupDto> login(@RequestBody CredentialsDto credentialsDto) {
        ManagerGroupDto managerDataDto = managerAuthService.login(credentialsDto);
        String token = jwtService.createToken(managerDataDto.getUsername());
        HttpHeaders headers = createHeadersWithCookie(token);
        tokenService.saveToken(managerDataDto.getUsername(), token);

        return ResponseEntity.ok().headers(headers).body(managerDataDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @AuthenticationPrincipal String name) {
        managerAuthService.logout(name);
        return ResponseEntity.ok().body("Logout successfully!");
    }

    @GetMapping("/refresh")
    public ResponseEntity<ManagerGroupDto> refresh(
            @AuthenticationPrincipal String name) {
        ManagerGroupDto managerGroupDto = managerAuthService.refresh(name);
        String token = jwtService.createToken(managerGroupDto.getUsername());
        HttpHeaders headers = createHeadersWithCookie(token);
        tokenService.saveToken(managerGroupDto.getUsername(), token);

        return ResponseEntity.ok().headers(headers).body(managerGroupDto);
    }

    private HttpHeaders createHeadersWithCookie(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, JwtManagerGroupAuthenticationFilter.JWT_KEY +
                "=" + token + "; Path=/; HttpOnly; " + "SameSite=None; Secure");

        return headers;
    }
}
