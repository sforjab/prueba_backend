package com.uoc.tfm.vet_connect.consulta.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uoc.tfm.vet_connect.consulta.model.Consulta;
import com.uoc.tfm.vet_connect.consulta.repository.ConsultaRepository;
import com.uoc.tfm.vet_connect.mascota.service.MascotaService;

import jakarta.transaction.Transactional;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MascotaService mascotaService;

    @Transactional
    public Optional<Consulta> getConsultaPorId(Long id) {
        return consultaRepository.findById(id);
    }

    public List<Consulta> getConsultasPorFecha(LocalDateTime fecha) {
        return consultaRepository.findByFechaConsulta(fecha);
    }
}
