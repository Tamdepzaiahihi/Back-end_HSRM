package com.HSRMApp.service;

import com.HSRMApp.dto.CredentialsDto;
import com.HSRMApp.dto.ManagerGroupDto;
import com.HSRMApp.entity.ManagerGroup;
import com.HSRMApp.entity.User;
import com.HSRMApp.exception.RestException;
import com.HSRMApp.repository.ManagerGroupRepository;
import com.HSRMApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerGroupAccountService {

        private final UserRepository userRepository;
        private final ManagerGroupRepository managerRepository;
        private final PasswordEncoder passwordEncoder;

        public ManagerGroupDto getAccount(String name) {
                User user = userRepository.findByUsername(name)
                                .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "User not found!"));

                ManagerGroup manager = managerRepository.findByUserId(user.getId())
                                .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "Manager not found!"));

                return ManagerGroupDto.builder().username(user.getUsername()).firstName(manager.getFirstName())
                                .lastName(manager.getLastName()).build();
        }

        public void updateAccount(ManagerGroupDto managerGroupDto) {
                ManagerGroup manager = managerRepository.findById(managerGroupDto.getUsername())
                                .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "Manager not found!"));

                manager.setFirstName(managerGroupDto.getFirstName());
                manager.setLastName(managerGroupDto.getLastName());


                managerRepository.save(manager);
        }

        public void updatePassword(CredentialsDto oldCredentials, CredentialsDto newCredentials) {
                User user = userRepository.findByUsername(oldCredentials.getUsername())
                                .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "User not found!"));

                if (!passwordEncoder.matches(oldCredentials.getPassword(), user.getPassword())) {
                        throw new RestException(HttpStatus.BAD_REQUEST, "Wrong password!");
                }

                user.setPassword(passwordEncoder.encode(newCredentials.getPassword()));
                userRepository.save(user);
        }
}
