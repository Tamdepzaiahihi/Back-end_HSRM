package com.HSRMApp.service;


import com.HSRMApp.dto.CredentialsDto;
import com.HSRMApp.dto.ManagerGroupDto;
import com.HSRMApp.entity.ManagerGroup;
import com.HSRMApp.entity.Role;
import com.HSRMApp.entity.User;
import com.HSRMApp.exception.RestException;
import com.HSRMApp.repository.ManagerGroupRepository;
import com.HSRMApp.repository.TokenRepository;
import com.HSRMApp.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerGroupAuthService {

    private final ManagerGroupRepository managerRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    @PostConstruct
    public void initAdmin() {
        User admin = User.builder().username("admin").password(passwordEncoder.encode("admin"))
                .role(Role.MANAGER).build();

        ManagerGroup manager = ManagerGroup.builder().firstName("Admin").lastName("Admin").user(admin)
                .build();

        managerRepository.save(manager);
    }


    public ManagerGroupDto login(CredentialsDto credentialsDto) {
        String username = credentialsDto.getUsername();
        String password = credentialsDto.getPassword();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RestException(HttpStatus.BAD_REQUEST, "Invalid credentials");
        }

        ManagerGroup manager = managerRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "Manager not found"));

        return ManagerGroupDto.builder().uuid(manager.getUuid()).firstName(manager.getFirstName())
                .lastName(manager.getLastName()).username(user.getUsername()).build();
    }

    public void logout(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RestException(HttpStatus.UNAUTHORIZED,
                                                     "Invalid " + "credentials"));

        tokenRepository.deleteById(user.getId());
        SecurityContextHolder.clearContext();
    }

    public ManagerGroupDto refresh(String name) {
        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new RestException(HttpStatus.UNAUTHORIZED,
                                                     "Invalid " + "credentials"));

        ManagerGroup manager = managerRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "Manager not found"));

        return ManagerGroupDto.builder().username(user.getUsername()).firstName(manager.getFirstName())
                .lastName(manager.getLastName()).build();
    }
}
