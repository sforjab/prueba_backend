package com.uoc.tfm.vet_connect.prueba.model;

import java.time.LocalDate;
import java.util.List;

import com.uoc.tfm.vet_connect.consulta.model.Consulta;
import com.uoc.tfm.vet_connect.mascota.model.Mascota;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Prueba {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    TipoPrueba tipo;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column
    private String documentoAdjunto;

    @Column(length = 500)
    private String resultado;

    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    /* @ManyToOne
    @JoinColumn(name = "veterinario_id", nullable = false)
    private Usuario veterinario; */

    @OneToMany(mappedBy = "prueba", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocumentoPrueba> documentosPrueba;



}
