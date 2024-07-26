package com.uoc.tfm.vet_connect.mascota.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uoc.tfm.vet_connect.mascota.model.Mascota;
import com.uoc.tfm.vet_connect.mascota.service.MascotaService;

@RestController
@RequestMapping("/mascotas")
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
