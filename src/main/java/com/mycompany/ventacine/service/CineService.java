package com.mycompany.ventacine.service;

import com.mycompany.ventacine.model.Cine;
import com.mycompany.ventacine.repository.CineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CineService {

    @Autowired
    private CineRepository cineRepository;

    public List<Cine> listarTodos() {
        return cineRepository.findAll();
    }

    public Optional<Cine> buscarPorId(Long id) {
        return cineRepository.findById(id);
    }

    public Cine guardar(Cine cine) {
        return cineRepository.save(cine);
    }

    public void eliminar(Long id) {
        cineRepository.deleteById(id);
    }
}
