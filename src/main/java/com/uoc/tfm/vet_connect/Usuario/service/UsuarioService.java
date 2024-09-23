package com.uoc.tfm.vet_connect.usuario.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uoc.tfm.vet_connect.usuario.model.Rol;
import com.uoc.tfm.vet_connect.usuario.model.Usuario;
import com.uoc.tfm.vet_connect.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    /* public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    } */

    public Optional<Usuario> getUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> getUsuarioPorNumIdent(String numIdent) {
        return usuarioRepository.findByNumIdent(numIdent);
    }

    public Optional<Usuario> getUsuarioPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Transactional
    public Optional<Usuario> createUsuario(Usuario usuario) {
        try {
            // Validación de 'numIdent' único
            if (usuarioRepository.findByNumIdent(usuario.getNumIdent()).isPresent()) {
                return Optional.empty();
            }

            // Validación de 'username' único
            if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
                return Optional.empty();
            }

            // Solo permite asociar una clínica si el rol es VETERINARIO o ADMIN_CLINICA
            if ((usuario.getRol() == Rol.VETERINARIO || usuario.getRol() == Rol.ADMIN_CLINICA) && usuario.getClinica() == null) {
                return Optional.empty(); // No se puede crear un veterinario o admin de clínica sin asociar una clínica
            }

            // Se encripta la contraseña antes de guardar
            String passwordCodificado = passwordEncoder.encode(usuario.getPassword());
            usuario.setPassword(passwordCodificado);

            Usuario usuarioCreado = usuarioRepository.save(usuario);
            return Optional.of(usuarioCreado);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Transactional
    public Optional<Usuario> updateUsuario(Long id, Usuario usuarioModificado) {
        try {
            Optional<Usuario> usuario = usuarioRepository.findById(id);
            if (!usuario.isPresent()) {
                return Optional.empty(); // Si el usuario no existe, se devuelve un 'Optional' vacío
            }

            // Validación de 'numIdent' único
            Optional<Usuario> usuarioMismoNumIdent = usuarioRepository.findByNumIdent(usuarioModificado.getNumIdent());
            if (usuarioMismoNumIdent.isPresent() && !usuarioMismoNumIdent.get().getId().equals(id)) {
                return Optional.empty(); // Si otro usuario tiene el mismo 'numIdent', devolvemos un 'Optional' vacío
            }

            // Validación de 'username' único
            Optional<Usuario> usuarioMismoUsername = usuarioRepository.findByUsername(usuarioModificado.getUsername());
            if (usuarioMismoUsername.isPresent() && !usuarioMismoUsername.get().getId().equals(id)) {
                return Optional.empty(); // Si otro usuario tiene el mismo 'username', se devuelve 'Optional' vacío
            }

            // Copiamos todas las propiedades del objeto 'usuarioModificado' a 'usuario', excluyendo el 'id' y 'password'
            BeanUtils.copyProperties(usuarioModificado, usuario.get(), "id", "password");

            // Guardamos y devolvemos el usuario actualizado
            Usuario usuarioAct = usuarioRepository.save(usuario.get());
            return Optional.of(usuarioAct);
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
