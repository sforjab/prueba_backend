package com.uoc.tfm.vet_connect.mascota.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uoc.tfm.vet_connect.mascota.model.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository <Mascota, Long> {

}
