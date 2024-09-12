package com.uoc.tfm.vet_connect.usuario.controller;

import org.springframework.web.bind.annotation.RestController;

import com.uoc.tfm.vet_connect.error.ApiError;
import com.uoc.tfm.vet_connect.jwt.JwtService;
import com.uoc.tfm.vet_connect.usuario.model.Usuario;
import com.uoc.tfm.vet_connect.usuario.service.UsuarioService;

import io.jsonwebtoken.Claims;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;
    
    // Cargar el archivo .env
    /* Dotenv dotenv = Dotenv.configure().load(); */

    /* @GetMapping("/getUsuarios")
    public ResponseEntity<List<Usuario>> getUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();

        return ResponseEntity.ok(usuarios);
    } */

    @GetMapping("/getUsuarioPorId/{id}")
    public ResponseEntity<Object> getUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioPorId(id);

        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new ApiError("Usuario no encontrado con ID: " + id));
        }
    }

    @GetMapping("/getUsuarioPorNumIdent/{numIdent}")
    public ResponseEntity<Object> getUsuarioPorNumIdent(@PathVariable String numIdent) {
        Optional<Usuario> usuario = usuarioService.getUsuarioPorNumIdent(numIdent);
        
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new ApiError("Usuario no encontrado con número de identificación: " + numIdent));
        }
    }

    @GetMapping("/getUsuarioPorUsername/{username}")
    public ResponseEntity<Object> getUsuarioPorUsername(@PathVariable String username) {
        Optional<Usuario> usuario = usuarioService.getUsuarioPorUsername(username);
        
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new ApiError("Usuario no encontrado con nombre de usuario: " + username));
        }
    }

    // Método para obtener el usuario logueado desde el token
    @GetMapping("/getUsuarioLogueado")
    public ResponseEntity<Object> getUsuarioLogueado(@RequestHeader("Authorization") String token) {
        try {
            // Eliminamos el prefijo "Bearer " del token
            token = token.replace("Bearer ", "");

            // Decodificamos el token sin validarlo
            Claims claims = jwtService.extractAllClaims(token);

            // Extraemos el "subject" (el username) del token decodificado
            String username = claims.getSubject();  // El subject es el username por defecto en el token

            // Luego, buscas al usuario por su username en la base de datos
            Optional<Usuario> usuario = usuarioService.getUsuarioPorUsername(username);

            if (usuario.isPresent()) {
                return ResponseEntity.ok(usuario.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiError("Usuario no encontrado"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiError("Token inválido o usuario no encontrado"));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> createdUsuario = usuarioService.createUsuario(usuario);

        if (createdUsuario.isPresent()) {
            return ResponseEntity.ok(createdUsuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                                 .body(new ApiError("Ya existe un usuario con el mismo número de identificación o nombre de usuario."));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> updatedUsuario = usuarioService.updateUsuario(id, usuario);

        if (updatedUsuario.isPresent()) {
            return ResponseEntity.ok(updatedUsuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                                 .body(new ApiError("No se pudo actualizar. Usuario no encontrado, número de identificación o nombre de usuario duplicado."));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable Long id) {
        boolean isDeleted = usuarioService.deleteUsuario(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new ApiError("Usuario no encontrado con ID: " + id));
        }
    }
}
