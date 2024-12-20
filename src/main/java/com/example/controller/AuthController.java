package com.example.controller;

import com.example.config.JwtUtil;
import com.example.dto.AuthRequest;
import com.example.dto.AuthResponse;
import com.example.entity.Role;
import com.example.service.UserService;
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
