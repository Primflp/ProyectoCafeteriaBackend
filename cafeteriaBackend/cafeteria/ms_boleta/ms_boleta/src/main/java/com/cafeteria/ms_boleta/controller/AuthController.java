package com.cafeteria.ms_boleta.controller;

import com.cafeteria.ms_boleta.config.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        String user = credenciales.get("username");
        String pass = credenciales.get("password");

        if ("admin".equals(user) && "1234".equals(pass)) {
            String token = jwtService.generarToken(user, "ROLE_ADMIN");
            return ResponseEntity.ok(Map.of("token", token));
        }

        return ResponseEntity.status(401).body(Map.of("error", "Credenciales inválidas"));
    }
}