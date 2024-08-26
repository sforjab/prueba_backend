package com.uoc.tfm.vet_connect.usuario.controller;

import org.springframework.web.bind.annotation.RestController;

import com.uoc.tfm.vet_connect.usuario.model.Usuario;
import com.uoc.tfm.vet_connect.usuario.service.UsuarioService;

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
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    /* @GetMapping("/getUsuarios")
    public ResponseEntity<List<Usuario>> getUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();

        return ResponseEntity.ok(usuarios);
    } */

    @GetMapping("/getUsuarioPorId/{id}")
    public ResponseEntity<Usuario> getUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioPorId(id);

        return usuario.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getUsuarioPorNumIdent/{numIdent}")
    public ResponseEntity<Usuario> getUsuarioPorNumIdent(@PathVariable String numIdent) {
        Optional<Usuario> usuario = usuarioService.getUsuarioPorNumIdent(numIdent);
        
        return usuario.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getUsuarioPorUsername/{username}")
    public ResponseEntity<Usuario> getUsuarioPorUsername(@PathVariable String username) {
        Optional<Usuario> usuario = usuarioService.getUsuarioPorUsername(username);
        
        return usuario.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> createdUsuario = usuarioService.createUsuario(usuario);

        return createdUsuario.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> updatedUsuario = usuarioService.updateUsuario(id, usuario);

        return updatedUsuario.map(ResponseEntity::ok)
                             .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        boolean isDeleted = usuarioService.deleteUsuario(id);
        if(isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
