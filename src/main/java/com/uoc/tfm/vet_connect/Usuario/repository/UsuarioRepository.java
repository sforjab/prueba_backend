package com.uoc.tfm.vet_connect.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uoc.tfm.vet_connect.usuario.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    Optional<Usuario> findByNumIdent(String numIdent);
    Optional<Usuario> findByUsername(String username);
}
