package com.HSRMApp.service;

import com.HSRMApp.entity.Token;
import com.HSRMApp.entity.User;
import com.HSRMApp.exception.RestException;
import com.HSRMApp.repository.TokenRepository;
import com.HSRMApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    public void saveToken(String username, String token) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, "User not found");
        }

        tokenRepository.saveAndFlush(Token.builder().user(user.get()).token(token).build());
    }

    public void deleteToken(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, "User not found");
        }

        tokenRepository.deleteByUser(user.get());
    }
}
