package com.example.studentmanagementapi.service.impl;

import com.example.studentmanagementapi.config.JwtAuthenticationFilter;
import com.example.studentmanagementapi.dao.AuthenticationResponse;
import com.example.studentmanagementapi.dao.RefreshTokenRequest;
import com.example.studentmanagementapi.dao.SignInRequest;
import com.example.studentmanagementapi.dao.SignUpRequest;
import com.example.studentmanagementapi.dto.User;
import com.example.studentmanagementapi.repository.UserRepository;
import com.example.studentmanagementapi.service.AuthenticationService;
import com.example.studentmanagementapi.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse signIn(SignInRequest request) {
        System.out.println(request.getUsername());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwt);
        authenticationResponse.setRefreshToken(refreshToken);
        return authenticationResponse;
    }

    @Override
    public User signUp(SignUpRequest request) {
        System.out.println(request.getEmail());
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        //user.setPassword(request.getPassword());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        return userRepository.save(user);
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String username = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByUsername(username).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(),user)){
            var jwt = jwtService.generateToken(user);
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setToken(jwt);
            authenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return authenticationResponse;
        }
        return null;
    }
}
