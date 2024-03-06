package com.example.studentmanagementapi.service;

import com.example.studentmanagementapi.dao.AuthenticationResponse;
import com.example.studentmanagementapi.dao.RefreshTokenRequest;
import com.example.studentmanagementapi.dao.SignInRequest;
import com.example.studentmanagementapi.dao.SignUpRequest;
import com.example.studentmanagementapi.dto.User;

public interface AuthenticationService {
    AuthenticationResponse signIn(SignInRequest signinRequest);

    User signUp(SignUpRequest signUpRequest);
    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
