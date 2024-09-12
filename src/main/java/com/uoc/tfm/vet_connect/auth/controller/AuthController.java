package com.uoc.tfm.vet_connect.auth.controller;

import com.uoc.tfm.vet_connect.auth.model.AuthResponse;
import com.uoc.tfm.vet_connect.auth.model.LoginRequest;
import com.uoc.tfm.vet_connect.auth.model.RegistroRequest;
import com.uoc.tfm.vet_connect.jwt.JwtService;
import com.uoc.tfm.vet_connect.usuario.model.Usuario;
import com.uoc.tfm.vet_connect.usuario.model.Rol;
import com.uoc.tfm.vet_connect.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        Optional<Usuario> usuario = usuarioService.getUsuarioPorUsername(loginRequest.getUsername());

        if (usuario.isEmpty()) {
            System.out.println("Usuario no encontrado: " + loginRequest.getUsername());
        }

        if (usuario.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), usuario.get().getPassword())) {
            System.out.println("Usuario autenticado: " + loginRequest.getUsername());
            String token = jwtService.generateToken(usuario.get());
            return ResponseEntity.ok(new AuthResponse(token, usuario.get().getRol().toString()));
        }

        System.out.println("Fallo en la autenticación para usuario: " + loginRequest.getUsername());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody RegistroRequest registroRequest) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(registroRequest.getUsername());
        nuevoUsuario.setPassword(passwordEncoder.encode(registroRequest.getPassword()));
        nuevoUsuario.setEmail(registroRequest.getEmail());
        nuevoUsuario.setRol(Rol.CLIENTE);  // Define el rol por defecto, por ejemplo CLIENTE

        Optional<Usuario> usuarioCreado = usuarioService.createUsuario(nuevoUsuario);

        return usuarioCreado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
