package com.HSRMApp.service;

import com.HSRMApp.dto.CredentialsDto;
import com.HSRMApp.dto.RegisterDto;
import com.HSRMApp.dto.UserDto;
import com.HSRMApp.entity.BasicStaffGroup;
import com.HSRMApp.entity.Role;
import com.HSRMApp.entity.User;
import com.HSRMApp.exception.RestException;
import com.HSRMApp.repository.BasicStaffGroupRepository;
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
public class BasicStaffGroupAuthService {

    private final UserRepository userRepository;
    private final BasicStaffGroupRepository staffRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initDefaultStaff() {
        if (!userRepository.existsByUsername("staff")) {
            User user = User.builder().username("staff").role(Role.BASIC_STAFF).build();
            user.setPassword(passwordEncoder.encode("12345asd"));

            BasicStaffGroup staff = BasicStaffGroup.builder().firstName("First name").lastName("Last name")
                    .user(user).build();

            staffRepository.save(staff);
        }
    }

    public UserDto login(CredentialsDto credentialsDto) {
        String username = credentialsDto.getUsername();
        String password = credentialsDto.getPassword();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RestException(HttpStatus.UNAUTHORIZED,
                                                     "Invalid " + "credentials"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RestException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        BasicStaffGroup staff = staffRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RestException(HttpStatus.UNAUTHORIZED,
                                                     "Invalid " + "credentials"));

        return UserDto.builder().uuid(staff.getUuid()).username(user.getUsername())
                .firstName(staff.getFirstName()).lastName(staff.getLastName()).build();
    }

    public UserDto register(RegisterDto registerDto) {
        String username = registerDto.getUsername();
        String password = registerDto.getPassword();
        String firstName = registerDto.getFirstName();
        String lastName = registerDto.getLastName();

        if (firstName == null || firstName.isEmpty()) {
            throw new RestException(HttpStatus.BAD_REQUEST, "First name is required");
        }

        if (lastName == null || lastName.isEmpty()) {
            throw new RestException(HttpStatus.BAD_REQUEST, "Last name is required");
        }

        if (username == null || username.isEmpty()) {
            throw new RestException(HttpStatus.BAD_REQUEST, "Username is required");
        }

        if (password == null || password.isEmpty()) {
            throw new RestException(HttpStatus.BAD_REQUEST, "Password is required");
        }

        if (userRepository.existsByUsername(username)) {
            throw new RestException(HttpStatus.BAD_REQUEST, String.format(
                    "Username %s already " + "exists", username));
        }

        User user = User.builder().username(username).role(Role.BASIC_STAFF).build();
        user.setPassword(passwordEncoder.encode(password));

        BasicStaffGroup staff = BasicStaffGroup.builder().firstName(firstName).lastName(lastName).user(user)
                .build();

        BasicStaffGroup savedStaff = staffRepository.save(staff);

        return UserDto.builder().uuid(savedStaff.getUuid()).username(savedStaff.getUser()
                                                                                .getUsername())
                .firstName(savedStaff.getFirstName()).lastName(savedStaff.getLastName())
                .build();
    }

    public void logout(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RestException(HttpStatus.UNAUTHORIZED,
                                                     "Invalid " + "credentials"));

        tokenRepository.deleteById(user.getId());
        SecurityContextHolder.clearContext();
    }

    public void changePassword(String username, CredentialsDto credentialsDto) {
        String password = credentialsDto.getPassword();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RestException(HttpStatus.UNAUTHORIZED,
                                                     "Invalid " + "credentials"));

        user.setPassword(passwordEncoder.encode(password));
        userRepository.saveAndFlush(user);
    }
}
