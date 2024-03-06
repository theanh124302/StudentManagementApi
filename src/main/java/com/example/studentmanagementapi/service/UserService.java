package com.example.studentmanagementapi.service;

import java.util.List;
import java.util.Optional;

import com.example.studentmanagementapi.dto.Student;
import com.example.studentmanagementapi.dto.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();

}
