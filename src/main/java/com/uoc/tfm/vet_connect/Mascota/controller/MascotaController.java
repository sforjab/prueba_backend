package com.uoc.tfm.vet_connect.mascota.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uoc.tfm.vet_connect.mascota.model.MascotaDTO;
import com.uoc.tfm.vet_connect.mascota.service.MascotaService;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    @Autowired
    MascotaService mascotaService;

    @GetMapping("/getMascotaPorId/{id}")
    public ResponseEntity<MascotaDTO> getMascotaPorId(@PathVariable Long id) {
        Optional<MascotaDTO> mascota = mascotaService.getMascotaPorId(id);

        return mascota.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getMascotaPorNumChip/{numChip}")
    public ResponseEntity<MascotaDTO> getMascotaPorNumChip(@PathVariable String numChip) {
        Optional<MascotaDTO> mascota = mascotaService.getMascotaPorNumChip(numChip);

        return mascota.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getMascotaPorNombre/{nombre}")
    public ResponseEntity<List<MascotaDTO>> getMascotaPorNombre(@PathVariable String nombre) {
        List<MascotaDTO> mascotas = mascotaService.getMascotaPorNombre(nombre);

        if (mascotas.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(mascotas);
        }
    }

    @GetMapping("/getMascotasPorIdUsuario/{idUsuario}")
    public ResponseEntity<List<MascotaDTO>> getMascotasPorIdUsuario(@PathVariable Long idUsuario) {
        List<MascotaDTO> mascotas = mascotaService.getMascotasPorIdUsuario(idUsuario);

        if (mascotas.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(mascotas);
        }
    }
}
