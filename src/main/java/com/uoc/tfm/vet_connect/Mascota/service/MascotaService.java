package com.uoc.tfm.vet_connect.mascota.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uoc.tfm.vet_connect.mascota.model.MascotaDTO;
import com.uoc.tfm.vet_connect.mascota.repository.MascotaRepository;
import com.uoc.tfm.vet_connect.usuario.model.UsuarioDTO;
import com.uoc.tfm.vet_connect.usuario.service.UsuarioService;

@Service
public class MascotaService {

    @Autowired
    MascotaRepository mascotaRepository;

    @Autowired
    UsuarioService usuarioService;

    public Optional<MascotaDTO> getMascotaPorId(Long id) {
        return mascotaRepository.findById(id);
    }
    
    public Optional<MascotaDTO> getMascotaPorNumChip(String numChip) {
        return mascotaRepository.findByNumChip(numChip);
    }

    public List<MascotaDTO> getMascotaPorNombre(String nombre) {
        return mascotaRepository.findByNombre(nombre);
    }

    public List<MascotaDTO> getMascotasPorIdUsuario(Long idUsuario) {
        Optional<UsuarioDTO> usuario = usuarioService.getUsuarioPorId(idUsuario);
        return usuario.map(UsuarioDTO::getMascotas).orElseGet(List::of);
    }
}
