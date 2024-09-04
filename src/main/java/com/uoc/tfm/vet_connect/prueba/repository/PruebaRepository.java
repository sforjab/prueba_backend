package com.uoc.tfm.vet_connect.prueba.repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uoc.tfm.vet_connect.prueba.model.Prueba;


@Repository
public interface PruebaRepository extends JpaRepository<Prueba, Long> {

}
