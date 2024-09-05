package com.uoc.tfm.vet_connect.consulta.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uoc.tfm.vet_connect.consulta.model.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByFechaConsulta(LocalDateTime fecha);
    List<Consulta> findByMascotaId(Long mascotaId);
}
