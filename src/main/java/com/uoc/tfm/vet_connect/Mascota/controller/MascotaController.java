package com.uoc.tfm.vet_connect.mascota.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uoc.tfm.vet_connect.mascota.model.Mascota;
import com.uoc.tfm.vet_connect.mascota.service.MascotaService;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    @Autowired
    MascotaService mascotaService;

    @GetMapping("/getMascotaPorId/{id}")
    public ResponseEntity<Mascota> getMascotaPorId(@PathVariable Long id) {
        Optional<Mascota> mascota = mascotaService.getMascotaPorId(id);

        return mascota.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getMascotaPorNumChip/{numChip}")
    public ResponseEntity<Mascota> getMascotaPorNumChip(@PathVariable String numChip) {
        Optional<Mascota> mascota = mascotaService.getMascotaPorNumChip(numChip);

        return mascota.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getMascotaPorNombre/{nombre}")
    public ResponseEntity<List<Mascota>> getMascotaPorNombre(@PathVariable String nombre) {
        List<Mascota> mascotas = mascotaService.getMascotaPorNombre(nombre);

        if (mascotas.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(mascotas);
        }
    }

    @GetMapping("/getMascotasPorIdUsuario/{idUsuario}")
    public ResponseEntity<List<Mascota>> getMascotasPorIdUsuario(@PathVariable Long idUsuario) {
        List<Mascota> mascotas = mascotaService.getMascotasPorIdUsuario(idUsuario);

        if (mascotas.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(mascotas);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Mascota> createMascota(@RequestBody Mascota mascota) {
        Optional<Mascota> createdMascota = mascotaService.createMascota(mascota);

        return createdMascota.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mascota> updateMascota(@PathVariable Long id, @RequestBody Mascota mascota) {
        Optional<Mascota> updatedMascota = mascotaService.updateMascota(id, mascota);

        return updatedMascota.map(ResponseEntity::ok)
                             .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMascota(@PathVariable Long id) {
        boolean isDeleted = mascotaService.deleteMascota(id);
        if(isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
