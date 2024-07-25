package com.uoc.tfm.vet_connect.Mascota.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uoc.tfm.vet_connect.Mascota.model.Mascota;
import com.uoc.tfm.vet_connect.Mascota.repository.MascotaRepository;

@Service
public class MascotaService {

    @Autowired
    MascotaRepository mascotaRepository;

    public Optional<Mascota> getMascotaPorId(Long id) {
        return mascotaRepository.findById(id);
    }
}
