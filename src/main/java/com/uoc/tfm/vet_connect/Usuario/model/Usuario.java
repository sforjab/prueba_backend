package com.uoc.tfm.vet_connect.Usuario.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.uoc.tfm.vet_connect.Mascota.model.Mascota;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String numIdent;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido1;
    
    private String apellido2;
    private String direccion;
    private String telefono;
    private String email;
    Rol rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Mascota> mascotas;
}
