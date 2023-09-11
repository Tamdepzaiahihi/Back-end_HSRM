package com.HSRMApp.config;

import com.HSRMApp.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class JwtBasicStaffGroupAuthenticationFilter extends OncePerRequestFilter {
    public static final String JWT_KEY = "basic_staff_jwt";

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtBasicStaffGroupAuthenticationFilter.class);
    private final JwtService jwtService;

    @Override
    public boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().startsWith("/api/v1/basic_staff/auth") ||
                !request.getServletPath().startsWith("/api/v1/basic_staff");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String jwt = extractTokenFromRequest(request);
        LOGGER.info("JWT: {}", jwt);

        if (jwt != null && jwtService.validateToken(jwt))
            createAuthentication(jwt);

        filterChain.doFilter(request, response);
    }

    private void createAuthentication(String jwt) {
        try {
            UsernamePasswordAuthenticationToken authentication = jwtService.createAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            throw e;
        }
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null)
            return null;

        return Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(JWT_KEY))
                .map(Cookie::getValue).findAny().orElse(null);
    }
}
