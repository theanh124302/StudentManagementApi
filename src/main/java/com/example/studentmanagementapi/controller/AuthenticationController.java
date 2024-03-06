package com.example.studentmanagementapi.controller;
import com.example.studentmanagementapi.dao.AuthenticationResponse;
import com.example.studentmanagementapi.dao.RefreshTokenRequest;
import com.example.studentmanagementapi.dao.SignInRequest;
import com.example.studentmanagementapi.dao.SignUpRequest;
import com.example.studentmanagementapi.dto.User;
import com.example.studentmanagementapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signIn(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        System.out.println(refreshTokenRequest);
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}


