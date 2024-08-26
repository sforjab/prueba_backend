package com.uoc.tfm.vet_connect.auth.controller;

import com.uoc.tfm.vet_connect.auth.model.AuthResponseDTO;
import com.uoc.tfm.vet_connect.auth.model.LoginRequestDTO;
import com.uoc.tfm.vet_connect.auth.model.RegistroRequestDTO;
import com.uoc.tfm.vet_connect.jwt.JwtService;
import com.uoc.tfm.vet_connect.usuario.model.UsuarioDTO;
import com.uoc.tfm.vet_connect.usuario.model.RolDTO;
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
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        Optional<UsuarioDTO> usuario = usuarioService.getUsuarioPorUsername(loginRequest.getUsername());

        if (usuario.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), usuario.get().getPassword())) {
            String token = jwtService.generateToken(usuario.get());
            return ResponseEntity.ok(new AuthResponseDTO(token, usuario.get().getRol().toString()));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/registro")
    public ResponseEntity<UsuarioDTO> registrarUsuario(@RequestBody RegistroRequestDTO registroRequest) {
        UsuarioDTO nuevoUsuario = new UsuarioDTO();
        nuevoUsuario.setUsername(registroRequest.getUsername());
        nuevoUsuario.setPassword(passwordEncoder.encode(registroRequest.getPassword()));
        nuevoUsuario.setEmail(registroRequest.getEmail());
        nuevoUsuario.setRol(RolDTO.CLIENTE);  // Define el rol por defecto, por ejemplo CLIENTE

        Optional<UsuarioDTO> usuarioCreado = usuarioService.createUsuario(nuevoUsuario);

        return usuarioCreado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
