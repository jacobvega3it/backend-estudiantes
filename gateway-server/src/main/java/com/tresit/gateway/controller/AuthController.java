package com.tresit.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tresit.gateway.config.JwtProperties;
import com.tresit.gateway.dto.LoginRequest;
import com.tresit.gateway.dto.LoginResponse;
import com.tresit.gateway.util.JwtUtil;

import jakarta.validation.Valid;

/**
 * Controlador para gestionar la autenticación
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final JwtUtil jwtUtil;
    private final JwtProperties jwtProperties;

    public AuthController(JwtUtil jwtUtil, JwtProperties jwtProperties) {
        this.jwtUtil = jwtUtil;
        this.jwtProperties = jwtProperties;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        logger.info("Intento de login para usuario: {}", request.username());
        
        if (jwtProperties.getDefaultCredentials().getUsername().equals(request.username()) && 
            jwtProperties.getDefaultCredentials().getPassword().equals(request.password())) {
            
            String token = jwtUtil.generateToken(request.username());
            logger.info("Login exitoso para usuario: {}", request.username());
            return ResponseEntity.ok(new LoginResponse(token));
        }
        
        logger.warn("Login fallido para usuario: {}", request.username());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
