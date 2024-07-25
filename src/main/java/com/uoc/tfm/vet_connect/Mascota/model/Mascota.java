package com.uoc.tfm.vet_connect.Mascota.model;

import java.time.LocalDate;

import com.uoc.tfm.vet_connect.Usuario.model.Usuario;

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
public class Mascota {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String numChip;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String especie;

    @Column(nullable = false)
    private String raza;
    
    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
