package com.uoc.tfm.vet_connect.mascota.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uoc.tfm.vet_connect.mascota.model.Mascota;
import com.uoc.tfm.vet_connect.mascota.repository.MascotaRepository;
import com.uoc.tfm.vet_connect.usuario.model.Usuario;
import com.uoc.tfm.vet_connect.usuario.service.UsuarioService;

@Service
public class MascotaService {

    @Autowired
    MascotaRepository mascotaRepository;

    @Autowired
    UsuarioService usuarioService;

    public Optional<Mascota> getMascotaPorId(Long id) {
        return mascotaRepository.findById(id);
    }
    
    public Optional<Mascota> getMascotaPorNumChip(String numChip) {
        return mascotaRepository.findByNumChip(numChip);
    }

    public List<Mascota> getMascotaPorNombre(String nombre) {
        return mascotaRepository.findByNombre(nombre);
    }

    public List<Mascota> getMascotasPorIdUsuario(Long idUsuario) {
        Optional<Usuario> usuario = usuarioService.getUsuarioPorId(idUsuario);
        return usuario.map(Usuario::getMascotas).orElseGet(List::of);
    }

    @Transactional
    public Optional<Mascota> createMascota(Mascota mascota) {
        try {
            Mascota savedMascota = mascotaRepository.save(mascota);
            return Optional.of(savedMascota);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Transactional
    public Optional<Mascota> updateMascota(Long id, Mascota mascota) {
        try {
            return mascotaRepository.findById(id)
                    .map(existingMascota -> {
                        BeanUtils.copyProperties(mascota, existingMascota, "id");
                        return Optional.of(mascotaRepository.save(existingMascota));
                    })
                    .orElse(Optional.empty());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Transactional
    public boolean deleteMascota(Long id) {
        try {
            if(mascotaRepository.existsById(id)) {
                mascotaRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
