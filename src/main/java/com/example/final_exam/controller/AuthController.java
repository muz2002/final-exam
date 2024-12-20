package com.example.final_exam.controller;

import com.example.final_exam.config.JwtUtil;
import com.example.final_exam.dto.AuthRequest;
import com.example.final_exam.dto.AuthResponse;
import com.example.final_exam.entity.Role;
import com.example.final_exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        // Assume password is raw and must be encoded
        userService.register(request.getUsername(), request.getPassword(), Role.valueOf(request.getRole()));
        return "User registered";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtUtil.generateToken(request.getUsername());
        return new AuthResponse(token);
    }
}
