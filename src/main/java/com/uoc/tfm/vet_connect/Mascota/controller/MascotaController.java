package com.uoc.tfm.vet_connect.Mascota.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.uoc.tfm.vet_connect.Mascota.model.Mascota;
import com.uoc.tfm.vet_connect.Mascota.service.MascotaService;

public class MascotaController {

    @Autowired
    MascotaService mascotaService;

    @GetMapping("/getMascotaPorId/{id}")
    public ResponseEntity<Mascota> getMascotaPorId(@PathVariable Long id) {
        Optional<Mascota> mascota = mascotaService.getMascotaPorId(id);

        return mascota.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
