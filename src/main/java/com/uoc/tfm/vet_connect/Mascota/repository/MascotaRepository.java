package com.uoc.tfm.vet_connect.mascota.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uoc.tfm.vet_connect.mascota.model.MascotaDTO;

@Repository
public interface MascotaRepository extends JpaRepository <MascotaDTO, Long> {
    Optional<MascotaDTO> findByNumChip(String numChip);
    List<MascotaDTO> findByNombre(String nombre);
}
