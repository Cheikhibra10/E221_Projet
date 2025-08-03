package com.e221.pedagogieservice.runtime.controller;

import com.e221.pedagogieservice.domain.dtos.requests.LoginRequest;
import com.e221.pedagogieservice.domain.dtos.responses.LoginResponse;
import com.e221.pedagogieservice.runtime.services.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Gestion des authentifications")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(response);
    }
}
