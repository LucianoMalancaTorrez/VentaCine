package com.mycompany.ventacine.service;

import com.mycompany.ventacine.model.Sala;
import com.mycompany.ventacine.model.SalaVIP;
import com.mycompany.ventacine.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio para la gestión de salas (comunes y VIP).
 *
 * @author USUARIO
 */
@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public List<Sala> listarTodas() {
        return salaRepository.findAll();
    }

    public List<Sala> listarComunes() {
        return salaRepository.findAll().stream()
                .filter(s -> !(s instanceof SalaVIP))
                .collect(Collectors.toList());
    }

    public List<SalaVIP> listarVIP() {
        return salaRepository.findAll().stream()
                .filter(s -> s instanceof SalaVIP)
                .map(s -> (SalaVIP) s)
                .collect(Collectors.toList());
    }

    public Optional<Sala> buscarPorId(Long id) {
        return salaRepository.findById(id);
    }

    public Sala guardar(Sala sala) {
        return salaRepository.save(sala);
    }

    public void eliminar(Long id) {
        salaRepository.deleteById(id);
    }
}
