package com.motupa.ticketing.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.motupa.ticketing.entity.User;
import com.motupa.ticketing.exception.InvalidCredentialsException;
import com.motupa.ticketing.repository.UserRepository;
import com.motupa.ticketing.request.LoginRequest;
import com.motupa.ticketing.response.TokenResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional(readOnly = true)
    public TokenResponse login(LoginRequest request) {
        String email = request.email().trim().toLowerCase();
        User user = userRepository.findByEmail(email)
                .filter(u -> passwordEncoder.matches(request.password(), u.getPasswordHash()))
                .orElseThrow(InvalidCredentialsException::new);
        return new TokenResponse(jwtService.generateToken(user), "Bearer",
                jwtService.expiresInSeconds());
    }
}