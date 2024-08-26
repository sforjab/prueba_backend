package com.uoc.tfm.vet_connect.mascota.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uoc.tfm.vet_connect.mascota.model.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository <Mascota, Long> {
    Optional<Mascota> findByNumChip(String numChip);
    List<Mascota> findByNombre(String nombre);
}
