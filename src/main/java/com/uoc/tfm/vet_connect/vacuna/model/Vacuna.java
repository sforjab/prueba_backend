package com.uoc.tfm.vet_connect.vacuna.model;

import java.time.LocalDate;

import com.uoc.tfm.vet_connect.mascota.model.Mascota;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vacuna {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    String nombre;

    @Column(nullable = false)
    String laboratorio;

    @Column(nullable = false)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;
}
