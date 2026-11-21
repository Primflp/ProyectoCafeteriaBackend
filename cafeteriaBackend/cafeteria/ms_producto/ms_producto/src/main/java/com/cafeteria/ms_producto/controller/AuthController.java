package com.cafeteria.ms_producto.controller;

import com.cafeteria.ms_producto.config.JwtService;
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

        // Validación de ejemplo (puedes conectarlo a tu base de datos si tienes una entidad Usuario)
        if ("admin".equals(user) && "1234".equals(pass)) {
            String token = jwtService.generarToken(user, "ROLE_ADMIN");
            return ResponseEntity.ok(Map.of("token", token));
        }

        return ResponseEntity.status(401).body(Map.of("error", "Credenciales inválidas"));
    }
}