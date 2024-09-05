package com.uoc.tfm.vet_connect.vacuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uoc.tfm.vet_connect.vacuna.model.Vacuna;

public interface VacunaRepository extends JpaRepository<Vacuna, Long> {
    
}
