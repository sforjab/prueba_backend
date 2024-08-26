package com.uoc.tfm.vet_connect.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uoc.tfm.vet_connect.usuario.model.UsuarioDTO;

@Repository
public interface UsuarioRepository extends JpaRepository <UsuarioDTO, Long> {
    Optional<UsuarioDTO> findByNumIdent(String numIdent);
    Optional<UsuarioDTO> findByUsername(String username);
}
