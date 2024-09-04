package com.uoc.tfm.vet_connect.consulta.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import com.uoc.tfm.vet_connect.mascota.model.Mascota;
import com.uoc.tfm.vet_connect.prueba.model.Prueba;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Consulta {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaConsulta; // Fecha y hora de la consulta

    @Column(nullable = false)
    private String motivo; // Motivo de la consulta

    @Column(length = 1000)
    private String observaciones; // Observaciones realizadas por el veterinario

    // Relación con Mascota
    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

    // Relación con Pruebas asociadas a la consulta
    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL)
    private List<Prueba> pruebas; // Pruebas realizadas en la consulta
}
