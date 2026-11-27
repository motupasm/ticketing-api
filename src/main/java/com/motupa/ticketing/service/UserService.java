package com.motupa.ticketing.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.motupa.ticketing.entity.Role;
import com.motupa.ticketing.entity.User;
import com.motupa.ticketing.exception.EmailAlreadyUsedException;
import com.motupa.ticketing.repository.UserRepository;
import com.motupa.ticketing.request.RegisterRequest;
import com.motupa.ticketing.response.UserResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse register(RegisterRequest request) {
        String email = request.email().trim().toLowerCase();
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyUsedException(email);
        }
        User user = new User();
        user.setName(request.name().trim());
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);
        return UserResponse.from(userRepository.save(user));
    }
}
