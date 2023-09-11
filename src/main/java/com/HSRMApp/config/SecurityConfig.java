package com.HSRMApp.config;

import com.HSRMApp.entity.Role;
import com.HSRMApp.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    private final JwtService jwtService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint).and()
                .addFilterBefore(new JwtBasicStaffGroupAuthenticationFilter(jwtService),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtManagerGroupAuthenticationFilter(jwtService),
                        UsernamePasswordAuthenticationFilter.class)
                .csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeHttpRequests(requests -> requests.requestMatchers("/api/v1/basic_staff/auth/**")
                .permitAll());

        http.authorizeHttpRequests(requests -> requests.requestMatchers("/api/v1/basic_staff/**")
                .hasAuthority(Role.BASIC_STAFF.toString()));

        http.authorizeHttpRequests(requests -> requests.requestMatchers("/api/v1/manager/auth/**")
                .permitAll());

        http.authorizeHttpRequests(requests -> requests.requestMatchers("/api/v1/manager/**")
                .hasAuthority(Role.MANAGER.toString()));

        http.authorizeHttpRequests(requests -> requests.anyRequest().permitAll());

        return http.build();
    }
}
