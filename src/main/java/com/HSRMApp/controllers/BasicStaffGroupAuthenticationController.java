package com.HSRMApp.controllers;

import com.HSRMApp.config.JwtBasicStaffGroupAuthenticationFilter;
import com.HSRMApp.dto.CredentialsDto;
import com.HSRMApp.dto.RegisterDto;
import com.HSRMApp.dto.UserDto;
import com.HSRMApp.service.BasicStaffGroupAuthService;
import com.HSRMApp.service.JwtService;
import com.HSRMApp.service.TokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/basic_staff/auth")
@Transactional
@RequiredArgsConstructor
public class BasicStaffGroupAuthenticationController {

    private final BasicStaffGroupAuthService staffAuthService;
    private final JwtService jwtService;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(
            @RequestBody CredentialsDto credentialsDto) {
        UserDto userDto = staffAuthService.login(credentialsDto);
        String token = jwtService.createToken(userDto.getUsername());
        HttpHeaders headers = createHeadersWithCookie(token);
        tokenService.saveToken(userDto.getUsername(), token);

        return ResponseEntity.ok().headers(headers).body(userDto);
    }

    private HttpHeaders createHeadersWithCookie(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, JwtBasicStaffGroupAuthenticationFilter.JWT_KEY +
                "=" + token + "; Path=/; HttpOnly; " + "SameSite=None; Secure");

        return headers;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(
            @RequestBody RegisterDto registerDto) {
        UserDto userDto = staffAuthService.register(registerDto);
        String token = jwtService.createToken(userDto.getUsername());
        HttpHeaders headers = createHeadersWithCookie(token);
        tokenService.saveToken(userDto.getUsername(), token);

        return ResponseEntity.ok().headers(headers).body(userDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @AuthenticationPrincipal String name) {
        staffAuthService.logout(name);
        return ResponseEntity.ok().body("Logout successfully");
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refresh(
            @AuthenticationPrincipal String name) {
        String token = jwtService.createToken(name);
        HttpHeaders headers = createHeadersWithCookie(token);
        tokenService.saveToken(name, token);

        return ResponseEntity.ok().headers(headers).body("Refresh token successfully");
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @AuthenticationPrincipal String name, @RequestBody CredentialsDto credentialsDto) {
        staffAuthService.changePassword(name, credentialsDto);
        return ResponseEntity.ok().body("Change password successfully");
    }
}
