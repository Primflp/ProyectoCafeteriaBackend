package com.cafeteria.ms_usuario.controller;

import com.cafeteria.ms_usuario.config.JwtService;
import com.cafeteria.ms_usuario.model.Usuario;
import com.cafeteria.ms_usuario.repository.UsuarioRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    public AuthController(JwtService jwtService, UsuarioRepository usuarioRepository) {
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
    String email = credenciales.get("email");
    String pass = credenciales.get("password");

    var usuarioOpt = usuarioRepository.findByCorreoUsuario(email);
    if (usuarioOpt.isPresent()) {
        Usuario usuario = usuarioOpt.get();
        if (pass != null && pass.equals(usuario.getContrasenaUsuario())) {
            String token = jwtService.generarToken(usuario.getCorreoUsuario(), usuario.getRolUsuario());

            Map<String, Object> usuarioData = new java.util.HashMap<>();
            usuarioData.put("idUsuario", usuario.getIdUsuario());
            usuarioData.put("correoUsuario", usuario.getCorreoUsuario());
            usuarioData.put("nombresUsuario", usuario.getNombresUsuario());
            usuarioData.put("apellidosUsuario", usuario.getApellidosUsuario());
            usuarioData.put("rolUsuario", usuario.getRolUsuario());

            Map<String, Object> respuesta = new java.util.HashMap<>();
            respuesta.put("token", token);
            respuesta.put("usuario", usuarioData);

            return ResponseEntity.ok(respuesta);
        }
    }

    if ("admin@duoc.cl".equals(email) && "bestadmin".equals(pass)) {
        String token = jwtService.generarToken(email, "ROLE_ADMIN");
        return ResponseEntity.ok(Map.of(
                "token", token,
                "usuario", Map.of(
                        "correoUsuario", email,
                        "rolUsuario", "ROLE_ADMIN"
                )
        ));
    }

    if ("vendedor@duoc.cl".equals(email) && "vendebest".equals(pass)) {
        String token = jwtService.generarToken(email, "ROLE_VENDEDOR");
        return ResponseEntity.ok(Map.of(
                "token", token,
                "usuario", Map.of(
                        "correoUsuario", email,
                        "rolUsuario", "ROLE_VENDEDOR"
                )
        ));
    }

    return ResponseEntity.status(401).body(Map.of("error", "Credenciales inválidas"));
}
    @PostMapping("/google")
    public ResponseEntity<?> loginConGoogle(@RequestBody Map<String, String> body) {
        String idTokenString = body.get("token");

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(), new GsonFactory())
            .setAudience(Collections.singletonList("745259504688-pokssjjth0m8qdm7unm3ca4al5omem1g.apps.googleusercontent.com"))
            .build();

        try {
            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                Payload payload = idToken.getPayload();
                String email = payload.getEmail();
                String nombre = (String) payload.get("given_name");
                String apellido = (String) payload.get("family_name");

                Usuario usuario = usuarioRepository.findByCorreoUsuario(email).orElseGet(() -> {
                    Usuario nuevo = new Usuario();
                    nuevo.setCorreoUsuario(email);
                    nuevo.setNombresUsuario(nombre != null ? nombre : "Usuario");
                    nuevo.setApellidosUsuario(apellido != null ? apellido : "Google");
                    nuevo.setRolUsuario("ROLE_CLIENTE");
                    nuevo.setEstadoCuentaUsuario("ACTIVO");
                    nuevo.setContrasenaUsuario(UUID.randomUUID().toString());
                    nuevo.setRunUsuario("G-" + UUID.randomUUID().toString().substring(0, 8));
                    
                    LocalDateTime ahora = LocalDateTime.now();
                    nuevo.setFechaCreacionUsuario(ahora);
                    nuevo.setFechaActualizacionUsuario(ahora);
                    return usuarioRepository.save(nuevo);
                });

                String jwtToken = jwtService.generarToken(usuario.getCorreoUsuario(), usuario.getRolUsuario());

                Map<String, Object> usuarioData = new java.util.HashMap<>();
                usuarioData.put("idUsuario", usuario.getIdUsuario());
                usuarioData.put("correoUsuario", usuario.getCorreoUsuario());
                usuarioData.put("nombresUsuario", usuario.getNombresUsuario());
                usuarioData.put("apellidosUsuario", usuario.getApellidosUsuario());
                usuarioData.put("rolUsuario", usuario.getRolUsuario());

                Map<String, Object> respuesta = new java.util.HashMap<>();
                respuesta.put("token", jwtToken);
                respuesta.put("usuario", usuarioData);

                return ResponseEntity.ok(respuesta);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token de Google inválido");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error verificando token");
        }
    }
}