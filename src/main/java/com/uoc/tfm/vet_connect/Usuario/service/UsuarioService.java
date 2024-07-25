package com.uoc.tfm.vet_connect.Usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uoc.tfm.vet_connect.Usuario.model.Usuario;
import com.uoc.tfm.vet_connect.Usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    /* public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    } */

    public Optional<Usuario> getUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> getUsuarioPorNumIdent(String numIdent) {
        return usuarioRepository.findByNumIdent(numIdent);
    }

    @Transactional
    public Optional<Usuario> createUsuario(Usuario usuario) {
        try {
            Usuario savedUsuario = usuarioRepository.save(usuario);
            return Optional.of(savedUsuario);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Transactional
    public Optional<Usuario> updateUsuario(Long id, Usuario usuario) {
        try {
            return usuarioRepository.findById(id)
                    .map(existingUsuario -> {
                        // Copia todas las propiedades del objeto usuario a existingUsuario
                        BeanUtils.copyProperties(usuario, existingUsuario, "id");
                        // Aquí "id" se excluye para evitar sobrescribir el ID del objeto existente

                        return Optional.of(usuarioRepository.save(existingUsuario));
                    })
                    .orElse(Optional.empty());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Transactional
    public boolean deleteUsuario(Long id) {
        try {
            if(usuarioRepository.existsById(id)) {
                usuarioRepository.deleteById(id);

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
