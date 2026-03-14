package com.vlad.ai_task_manager.service;

import com.vlad.ai_task_manager.config.CustomUserDetails;
import com.vlad.ai_task_manager.config.JwtService;
import com.vlad.ai_task_manager.dto.auth.AuthenticationRequest;
import com.vlad.ai_task_manager.dto.auth.AuthenticationResponse;
import com.vlad.ai_task_manager.dto.auth.RegisterRequest;
import com.vlad.ai_task_manager.persistance.entitys.User;
import com.vlad.ai_task_manager.persistance.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(new CustomUserDetails(user));
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(),request.password())
        );
        User user = userRepository.findByEmail(request.email()).orElseThrow();
        var jwtToken = jwtService.generateToken(new CustomUserDetails(user));
        return new AuthenticationResponse(jwtToken);
    }
}
