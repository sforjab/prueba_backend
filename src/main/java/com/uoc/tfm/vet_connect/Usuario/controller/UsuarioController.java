package com.uoc.tfm.vet_connect.Usuario.controller;

import org.springframework.web.bind.annotation.RestController;

import com.uoc.tfm.vet_connect.Usuario.model.Usuario;
import com.uoc.tfm.vet_connect.Usuario.service.UsuarioService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/create")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> createdUsuario = usuarioService.createUsuario(usuario);

        return createdUsuario.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/getUsuarios")
    public ResponseEntity<List<Usuario>> getUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/getUsuarioPorNumIdent/{numIdent}")
    public ResponseEntity<Usuario> getUsuarioPorNumIdent(@PathVariable String numIdent) {
        Optional<Usuario> usuario = usuarioService.getUsuarioPorNumIdent(numIdent);
        
        return usuario.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
