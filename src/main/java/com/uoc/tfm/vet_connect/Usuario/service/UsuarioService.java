package com.uoc.tfm.vet_connect.Usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uoc.tfm.vet_connect.Usuario.model.Usuario;
import com.uoc.tfm.vet_connect.Usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional
    public Optional<Usuario> createUsuario(Usuario usuario) {
        try {
            Usuario savedUsuario = usuarioRepository.save(usuario);
            return Optional.of(savedUsuario);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioPorNumIdent(String numIdent) {
        return usuarioRepository.findByNumIdent(numIdent);
    }
}
